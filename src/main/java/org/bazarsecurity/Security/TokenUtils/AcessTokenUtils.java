package org.bazarsecurity.Security.TokenUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.bazarsecurity.Model.Rol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AcessTokenUtils {
    //Obtenemos la cadena de caracteres del aplications
    @Value("${spring.security.jwt.firma}")
    private String firma;
    //Declaramos el secretkey
     SecretKey key;

    //Metodo para crear la llave secreta que se va a usar para firmar los tokens
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(firma.getBytes(StandardCharsets.UTF_8));
    }

    public String token(Authentication authentication) {

        // Creamos una lista para guardar los nombres de los roles del usuario
        List<String> listaRoles = new ArrayList<>();

        // Recorremos cada autoridad (rol) del objeto Authentication
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            // Agregamos el nombre del rol a la lista
            listaRoles.add(authority.getAuthority());
        }

        return buildToken(authentication.getName(), listaRoles);
    }


    public String token(String email, List<Rol> listaRol) {

        List<String> listaRoles = new ArrayList<>();

        // Recorremos cada autoridad (rol) del objeto Authentication
        for (Rol rol : listaRol) {
            // Agregamos el nombre del rol a la lista
            listaRoles.add(rol.getNombre());

        }
        return buildToken(email, listaRoles);
    }

    private String buildToken(String email, List<String> listaRoles) {

        // Obtenemos la fecha y hora actual como fecha de creación del token
        Instant fechaCreacion = Instant.now();
        // Calculamos la fecha de expiración sumando 5 minutos a la fecha de creación
        Instant fechaExpiracion = fechaCreacion.plus(Duration.ofMinutes(30));

        // Construimos el token JWT
        return Jwts
                .builder()// Asignamos el subject del token con el email del usuario
                .subject(email)// Agregamos un claim personalizado con la lista de roles
                .claim("roles", listaRoles)// Establecemos la fecha de creacion del token
                .issuedAt(Date.from(fechaCreacion))// Establecemos la fecha de expiración del token
                .expiration(Date.from(fechaExpiracion))// Firmamos el token con la clave secreta
                .signWith(key)// Compactamos en un String que representa el token final
                .compact();
    }

    public String obtenerEmail(String token) {

        // leemos el token JWT y verificamos su firma con la clave secreta
        Claims claims = Jwts
                .parser()          // Creamos un parser para el JWT
                .verifyWith(key)   // Verificamos la firma con la clave secreta
                .build()           // Construimos el parser
                .parseSignedClaims(token) // Parseamos el token
                .getPayload();     // Obtenemos los claims (información del token)

        // Retornamos el email del usuario
        return claims.getSubject();
    }

    public boolean validarToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(key)       // Verifica la firma del token con la clave secreta
                    .build()
                    .parseSignedClaims(token); // lee el token

            return true; // Si el token es valido,retornamos true
        } catch (JwtException e) {
            return false; // Si el token es invalido retornamos false
        }
    }
}