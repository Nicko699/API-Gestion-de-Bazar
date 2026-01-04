package org.bazarsecurity.Security.TokenUtils;

import org.bazarsecurity.Dto.RefreshToken.RefreshTokenResponseDto;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Model.RefreshToken;
import org.bazarsecurity.Model.Usuario;
import org.bazarsecurity.Repository.RefreshTokenRepository;
import org.bazarsecurity.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
// Servicio para gestionar la creación y manejo de Refresh Tokens
@Service
public class RefreshTokenUtils {

    private final UsuarioRepository usuarioRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;


    public RefreshTokenUtils(UsuarioRepository usuarioRepository, RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }
// Método para crear un nuevo Refresh Token para un usuario dado su email
    @Transactional
    public RefreshTokenResponseDto crearRefreshToken(String email) throws NotFoundException {
   // Buscar el usuario por email
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByEmail(email);
     
        if (usuario.isPresent()) {

            Usuario usuarioGet = usuario.get();
             // Invalidar cualquier token de refresco existente para el usuario
            Optional<RefreshToken> refreshTokenList = refreshTokenRepository.findRefreshTokenByUsuarioAndValido(usuarioGet, true);
// Si existe un token válido, marcarlo como inválido
            if (refreshTokenList.isPresent()) {
                RefreshToken refreshTokenGet = refreshTokenList.get();

                refreshTokenGet.setValido(false);

                refreshTokenRepository.save(refreshTokenGet);
            }
            // Eliminar tokens expirados antiguos
            refreshTokenRepository.deleteRefreshTokenByUsuarioAndFechaExpiracionBefore(usuarioGet,Instant.now().minus(Duration.ofDays(30)));
// Crear un nuevo token de refresco
            RefreshToken refreshToken = new RefreshToken();

            Instant fechaCreacion = Instant.now();
            Instant fechaExpiracion = fechaCreacion.plus(Duration.ofMinutes(60));

            String refreshTokenId = UUID.randomUUID().toString();

            String nuevoRefreshToken = UUID.randomUUID().toString();

            String nuevoRefreshTokenEncript = passwordEncoder.encode(nuevoRefreshToken);

            refreshToken.setRefreshTokenId(refreshTokenId);
            refreshToken.setRefreshTokenEncoded(nuevoRefreshTokenEncript);
            refreshToken.setFechaCreacion(fechaCreacion);
            refreshToken.setFechaExpiracion(fechaExpiracion);
            refreshToken.setValido(true);
            refreshToken.setUsuario(usuarioGet);


            refreshTokenRepository.save(refreshToken);
          // Devolver el ID del token y el token en texto plano
            return new RefreshTokenResponseDto(refreshTokenId, nuevoRefreshToken);

        } else {
            throw new NotFoundException("El usuario no se encuentra en el sistema");
        }
    }
}