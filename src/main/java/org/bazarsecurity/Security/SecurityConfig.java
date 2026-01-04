package org.bazarsecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// Configuración de seguridad para la aplicación usando JWT
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    // Maneja errores 401 cuando no hay autenticación válida
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    // Filtro que valida el JWT en cada request
    private final JwtAuthenticationFilter authenticationFilter;

    // Maneja errores 403 cuando un usuario autenticado no tiene permisos
    private final JwtAcessDeniedHandler acessDeniedHandler;

    public SecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter authenticationFilter, JwtAcessDeniedHandler acessDeniedHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
        this.acessDeniedHandler = acessDeniedHandler;
    }


    // Bean que expone el AuthenticationManager (se usa en login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Bean para encriptar contraseñas con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración principal de seguridad con JWT
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security
                // Deshabilitamos CSRF, login por formulario y auth básica porque usamos tokens JWT
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // Configuración de manejo de errores (401 y 403)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(acessDeniedHandler)
                )
                // Cada request debe traer su token, no se guarda sesión en servidor
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Reglas para la autorización:
                //requestMatchers permitimos cuales endpoints no necesitan de un inicio de sesion
                //de resto necesitará de autenticarse
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/usuario/crearCuenta","/usuario/iniciarSesion","/resetToken/cambiarPassword","/resetToken/recuperarPassword","/refreshToken/renovarAccessToken").permitAll()
                        .anyRequest().authenticated());

        // Registramos el filtro JWT antes del filtro de login por usuario/contraseña
        security.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }
}
