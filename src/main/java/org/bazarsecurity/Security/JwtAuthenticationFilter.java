package org.bazarsecurity.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bazarsecurity.Security.TokenUtils.AcessTokenUtils;;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
// Filtro de autenticación JWT personalizado
@Service
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final AcessTokenUtils accessTokenUtils;

    public JwtAuthenticationFilter(JwtUserDetailsService jwtUserDetailsService, AcessTokenUtils accessTokenUtils) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.accessTokenUtils = accessTokenUtils;
    }

    //Metodo para obtener el token Jwt desde la cabecera
    //Se obtiene el token jwt generado para comprobar la autenticacion del usuario
    //cada vez que haga una peticion
    private String obtenerToken(HttpServletRequest request){

        String bearerToken=request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){

            return bearerToken.substring(7);
        }

        return null;
    }


    // Metodo para validar el token JWT y guardar la información del usuario autenticado en el securityContext de spring.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Obtenemos el token desde el metodo que hicimos antes
        String token = obtenerToken(request);

        // Verificamos que el token no esté vacío y que sea válido
        if (StringUtils.hasText(token) && accessTokenUtils.validarToken(token)) {

            // Obtenemos el correo del usuario por el token jwt
            String correo = accessTokenUtils.obtenerEmail(token);

            // Cargamos los datos del usuario con el correo extraído en un objeto UserDetails
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(correo);

            // Creamos objeto de autenticación con los permisos del usuario
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Guardamos la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continuamos con los demás filtros
        filterChain.doFilter(request, response);
    }

}
