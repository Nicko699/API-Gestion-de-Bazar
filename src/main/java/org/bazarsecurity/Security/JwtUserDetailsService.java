package org.bazarsecurity.Security;

import org.bazarsecurity.Model.Rol;
import org.bazarsecurity.Model.Usuario;
import org.bazarsecurity.Repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
// Servicio para cargar los detalles del usuario para la autenticación JWT
@Service
public class JwtUserDetailsService implements UserDetailsService {

    //Realizamos inyeccion de dependencias por constructor
    private final UsuarioRepository usuarioRepository;

    public JwtUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Convertimos los roles en authoridades de GrantedAuthority para que spring las reconozca
    public Collection<GrantedAuthority> authorities(List<Rol> listaRol) {

        //Creamos una lista vacia de authoridades
        Collection<GrantedAuthority> authorities = new ArrayList<>();
//Recorremos la lista de roles que viene como parametro
        for (Rol rol : listaRol) {
            //Usamos la implementacion SimpleGrantedAuthority para trasformar el rol en una authoridad que spring la pueda reconocer
            GrantedAuthority authority = new SimpleGrantedAuthority(rol.getNombre());

            //Agregamos la authoridad  a la lista de GrantedAuthority
            authorities.add(authority);
        }
        //Retornamos las authoridades
        return authorities;
    }


    //Metodo para buscar el usuario en la bd y retornar un UserDetails
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //Encontramos el usuario en la bd
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByEmail(email);
//Conprobamos si el usuaro que viene está presente
        if (usuario.isPresent()) {
            Usuario usuarioGet = usuario.get();
            //retornamos un nuevo UserDetails con el email(identificador del usuario), su contraseña, si ese usuario esta activo o inactivo y los roles
            return new User(usuarioGet.getEmail(), usuarioGet.getPassword(), usuarioGet.isActive(), true, true, true, authorities(usuarioGet.getListaRol()));
        } else {
//Si el usuario no existe mandamos una excepcion
            throw new UsernameNotFoundException("Usuario no encontrado en nuestro sistema");
        }
    }
}
