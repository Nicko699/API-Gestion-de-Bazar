package org.bazarsecurity.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
// Manejador personalizado para accesos denegados en JWT
@Component
public class JwtAcessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        //Indicamos el tipo de respuesta http del mensaje
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //Indicamos que va a ser de tipo json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//Creamos un map para crear nuestro mensaje personalizado
        Map<String, String> mensaje = new HashMap<>();

        //Personalizamos el mensaje
        mensaje.put("tipoError", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
        mensaje.put("mensaje", "No tienes acceso a este recurso");
        //Convertimos el mensaje en un json
        String json = new ObjectMapper().writeValueAsString(mensaje);
//Sobreescribimos el mensaje de nosotros por el que viene por defecto
        response.getWriter().write(json);

    }
}
