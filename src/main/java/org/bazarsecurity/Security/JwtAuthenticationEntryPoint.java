package org.bazarsecurity.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
// Punto de entrada personalizado para la autenticación JWT
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
// Indicamos el código de estado HTTP de la respuesta
// SC_UNAUTHORIZED = 401, significa que el usuario **no está autenticado**
// y no tiene permiso para acceder al recurso solicitado.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //indicamos que el mensaje será de tipo json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//Creamos un map para crear nuestro mensaje personalizado
        Map<String, String> mensajeError = new HashMap<>();
//Personalizamos el mensaje
        mensajeError.put("tipoError", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
        mensajeError.put("mensaje", authException.getMessage());
//Convertimos el mensaje a un Json
        String json = new ObjectMapper().writeValueAsString(mensajeError);
//Sobreescribimos el mensaje de nosotros por el que viene por defecto
        response.getWriter().write(json);
    }
}
