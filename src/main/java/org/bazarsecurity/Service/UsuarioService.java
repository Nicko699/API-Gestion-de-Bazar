package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.Usuario.Crear.UsuarioCrearCuentaRequestDto;
import org.bazarsecurity.Dto.Usuario.Crear.UsuarioCrearCuentaResponseDto;
import org.bazarsecurity.Dto.Usuario.Editar.UsuarioEditarCuentaRequestDto;
import org.bazarsecurity.Dto.Usuario.Editar.UsuarioEditarCuentaResponseDto;
import org.bazarsecurity.Dto.Usuario.Login.UsuarioLoginRequestDto;
import org.bazarsecurity.Dto.Usuario.Login.UsuarioLoginResponseDto;
import org.bazarsecurity.Dto.Usuario.Obtener.UsuarioResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
// Servicio para gestionar las operaciones relacionadas con usuarios
public interface UsuarioService {

    public UsuarioCrearCuentaResponseDto crearCuenta(UsuarioCrearCuentaRequestDto crearCuentaRequestDto)throws BadRequestException;

    public UsuarioLoginResponseDto iniciarSesion(UsuarioLoginRequestDto loginRequestDto) throws NotFoundException;

    public UsuarioResponseDto obtenerUsuario(String email) throws  NotFoundException;

    public Page<UsuarioResponseDto>obtenerListaUsuarios( Boolean activo, Pageable pageable);

    public UsuarioEditarCuentaResponseDto editarUsuario(UserDetails userDetails, UsuarioEditarCuentaRequestDto editarCuentaRequestDto) throws BadRequestException;

    public void eliminarUsuario(Long id) throws NotFoundException;

    public void reactivarCuenta(Long id) throws NotFoundException, BadRequestException;
}
