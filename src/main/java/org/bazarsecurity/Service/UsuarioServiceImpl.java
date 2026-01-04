package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.RefreshToken.RefreshTokenResponseDto;
import org.bazarsecurity.Dto.Usuario.Crear.UsuarioCrearCuentaRequestDto;
import org.bazarsecurity.Dto.Usuario.Crear.UsuarioCrearCuentaResponseDto;
import org.bazarsecurity.Dto.Usuario.Editar.UsuarioEditarCuentaRequestDto;
import org.bazarsecurity.Dto.Usuario.Editar.UsuarioEditarCuentaResponseDto;
import org.bazarsecurity.Dto.Usuario.Login.UsuarioLoginRequestDto;
import org.bazarsecurity.Dto.Usuario.Login.UsuarioLoginResponseDto;
import org.bazarsecurity.Dto.Usuario.Obtener.UsuarioResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Mapper.UsuarioMapper;
import org.bazarsecurity.Model.Rol;
import org.bazarsecurity.Model.Usuario;
import org.bazarsecurity.Repository.RolRepository;
import org.bazarsecurity.Repository.UsuarioRepository;
import org.bazarsecurity.Security.TokenUtils.AcessTokenUtils;
import org.bazarsecurity.Security.TokenUtils.RefreshTokenUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Implementación del servicio para gestionar las operaciones relacionadas con usuarios
@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final AcessTokenUtils acessTokenUtils;
    private final RefreshTokenUtils refreshTokenUtils;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, RolRepository rolRepository, AuthenticationManager authenticationManager, AcessTokenUtils acessTokenUtils, RefreshTokenUtils refreshTokenUtils, PasswordEncoder passwordEncoder) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.authenticationManager = authenticationManager;
        this.acessTokenUtils = acessTokenUtils;
        this.refreshTokenUtils = refreshTokenUtils;
        this.passwordEncoder = passwordEncoder;
    }
    //método para crear una nueva cuenta de usuario
    @Transactional
    @Override
    public UsuarioCrearCuentaResponseDto crearCuenta(UsuarioCrearCuentaRequestDto crearCuentaRequestDto) throws BadRequestException {

        Usuario usuario = usuarioMapper.usuarioCrearCuentaRequestDtoToUsuario(crearCuentaRequestDto);
       // Verificamos si ya existe una cuenta con el mismo correo electrónico
        if (!usuarioRepository.existsUsuarioByEmail(usuario.getEmail())) {

            List<Rol> listaRol = new ArrayList<>();

            for (Long rol : crearCuentaRequestDto.getListaRol()) {

                Optional<Rol> rolEncontrado = rolRepository.findById(rol);
            //si el rol existe, lo añadimos a la lista de roles del usuario
                if (rolEncontrado.isPresent()) {
                    Rol rolGet = rolEncontrado.get();
                    listaRol.add(rolGet);
                } else {
                    throw new BadRequestException("Por favor, ingresa un id de rol valido: " + rol);
                }
            }
           // Encriptamos la contraseña del usuario antes de guardarla en la base de datos
            String passwordEncript=passwordEncoder.encode(usuario.getPassword());

            usuario.setPassword(passwordEncript);
            usuario.setListaRol(listaRol);
            usuario.setActive(true);

            Usuario usuarioGuardado = usuarioRepository.save(usuario);

            return usuarioMapper.usuarioToCrearCuentaResponseDto(usuarioGuardado);

        } else {
            throw new BadRequestException("Ya existe una cuenta con el correo: " + usuario.getEmail()+". Si su cuenta está inactiva, por favor activarla");
        }

    }
    //método para iniciar sesión de usuario
    @Override
    public UsuarioLoginResponseDto iniciarSesion(UsuarioLoginRequestDto loginRequestDto) throws NotFoundException {
       // Realizamos la autenticación del usuario con el email y la contraseña proporcionados
        Authentication authentication = authenticationManager.authenticate (
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
         // Establecemos la autenticación en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generamos el token de acceso y el token de refresco para el usuario autenticado
        String accessToken = acessTokenUtils.token(authentication);
        RefreshTokenResponseDto refreshToken = refreshTokenUtils.crearRefreshToken(loginRequestDto.getEmail());

        return new UsuarioLoginResponseDto(accessToken, "Bearer ", refreshToken);
    }
// Método para obtener los detalles de un usuario por email
@Transactional(readOnly = true)
    @Override
    public UsuarioResponseDto obtenerUsuario(String email) throws NotFoundException {

        Optional<Usuario>usuario=usuarioRepository.findUsuarioByEmail(email);

        if (usuario.isPresent()){

            Usuario usuarioGet=usuario.get();

            return usuarioMapper.usuarioToObtenerUsuarioResponseDto(usuarioGet);

        } else {
            throw new NotFoundException("El usuario no existe en el sistema con el correo: "+email); }
    }
// Método para obtener una lista paginada de usuarios, con filtro opcional por estado activo
    @Transactional(readOnly = true)
    @Override
    public Page<UsuarioResponseDto> obtenerListaUsuarios(Boolean activo, Pageable pageable) {

        Page<Usuario> listaUsuarios=Page.empty();
// Verificamos si el valor del parámetro activo es null para filtrar la lista de usuarios
        if (activo==null) {

            listaUsuarios = usuarioRepository.findAll(pageable);
        }
        //verificamos si el parámetro activo es verdadero para filtrar los usuarios activos o inactivos
        if (Boolean.TRUE.equals(activo)){

            listaUsuarios=usuarioRepository.findAllByActive(true,pageable);
        }
        // Si el parámetro activo es falso, filtramos los usuarios inactivos
        if (Boolean.FALSE.equals(activo)){
            listaUsuarios=usuarioRepository.findAllByActive(false,pageable);
        }

        List<UsuarioResponseDto>listaUsuarioDto=new ArrayList<>();

        for(Usuario usuario:listaUsuarios.getContent()){

            UsuarioResponseDto usuarioDto=usuarioMapper.usuarioToObtenerUsuarioResponseDto(usuario);

            listaUsuarioDto.add(usuarioDto);
        }

        return new PageImpl<>(listaUsuarioDto,pageable,listaUsuarios.getTotalElements());
    }
    // Método para editar la cuenta del usuario autenticado
    @Transactional
    @Override
    public UsuarioEditarCuentaResponseDto editarUsuario(UserDetails userDetails, UsuarioEditarCuentaRequestDto editarCuentaRequestDto) throws  BadRequestException {

        Optional<Usuario>usuario=usuarioRepository.findUsuarioByEmail(userDetails.getUsername());

        if (usuario.isPresent()){
            Usuario usuarioGet=usuario.get();
          // Verificamos que se haya enviado al menos un campo para editar
            if (editarCuentaRequestDto==null || (!StringUtils.hasText(editarCuentaRequestDto.getApellido())
                    && !StringUtils.hasText(editarCuentaRequestDto.getNombre()))){

                throw new BadRequestException("Debe enviar al menos un campo para editar");
            }

            Usuario usuarioEditado=usuarioMapper.usuarioEditarCuentaRequestDtoToUsuario(editarCuentaRequestDto,usuarioGet);

            usuarioRepository.save(usuarioEditado);

            return usuarioMapper.usuarioToUsuarioEditarCuentaResponseDto(usuarioEditado);
        }
        else {
            throw new RuntimeException("Error al procesar la solicitud"); }
    }
   // Método para eliminar/desactivar un usuario por ID
    @Transactional
    @Override
    public void eliminarUsuario(Long id) throws NotFoundException {

        Optional<Usuario>usuario=usuarioRepository.findById(id);

        if (usuario.isPresent()){

            Usuario usuarioGet=usuario.get(); usuarioGet.setActive(false);

            usuarioRepository.save(usuarioGet);

        } else {

            throw new RuntimeException("Error al procesar la solicitud");
        }
    }
// Método para reactivar la cuenta de un usuario por ID
  @Transactional
    @Override
    public void reactivarCuenta(Long id) throws NotFoundException, BadRequestException {

        Optional<Usuario>usuario=usuarioRepository.findById(id);

        if (usuario.isPresent()){

            Usuario usuarioGet=usuario.get();
// Verificamos si ya existe una cuenta activa con el mismo correo electrónico
            if (usuarioRepository.existsUsuarioByEmailAndActive(usuarioGet.getEmail(),true)){

                throw  new BadRequestException("Ya existe una cuenta activa con el email: "+usuarioGet.getEmail());
            }

            usuarioGet.setActive(true);

            usuarioRepository.save(usuarioGet);

        }
        else {
            throw new NotFoundException("El correo electronico ingresado no tiene una cuenta en nuestro sistema");
        }

    }
}
