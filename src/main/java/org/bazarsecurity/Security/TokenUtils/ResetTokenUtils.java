package org.bazarsecurity.Security.TokenUtils;

import org.bazarsecurity.Dto.ResetToken.ResetTokenResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Model.ResetToken;
import org.bazarsecurity.Model.Usuario;
import org.bazarsecurity.Repository.ResetTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

// Servicio para gestionar la creación y manejo de Reset Tokens
@Service
public class ResetTokenUtils {

    private final ResetTokenRepository resetTokenRepository;

    private final PasswordEncoder passwordEncoder;

    public ResetTokenUtils( ResetTokenRepository resetTokenRepository, PasswordEncoder passwordEncoder) {
        this.resetTokenRepository = resetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }
// Metodo para crear un nuevo Reset Token para un usuario dado
    @Transactional
    public ResetTokenResponseDto crearResetToken(Usuario usuario) throws BadRequestException {

   // Comprobar si ya existe un token válido para el usuario
        if (usuario.getResetToken() == null || !usuario.getResetToken().isValido() || usuario.getResetToken().getFechaExpiracion().isBefore(Instant.now())) {
// Si existe un token previo, eliminarlo
            if (usuario.getResetToken() != null) {

                resetTokenRepository.deleteResetTokenByUsuarioId(usuario.getId());
            }
            // Crear un nuevo token de reseteo
            ResetToken resetToken = new ResetToken();

            Instant fechaCreacion = Instant.now();
            Instant fechaExpiracion = fechaCreacion.plus(Duration.ofMinutes(15));
        
            String resetTokenId = UUID.randomUUID().toString();
            String resetTokenNew = UUID.randomUUID().toString();
    // Encriptar el token antes de almacenarlo
            String resetTokenNewEncript = passwordEncoder.encode(resetTokenNew);

            resetToken.setResetTokenId(resetTokenId);
            resetToken.setResetTokenEncoded(resetTokenNewEncript);
            resetToken.setFechaCreacion(fechaCreacion);
            resetToken.setFechaExpiracion(fechaExpiracion);
            resetToken.setValido(true);
            resetToken.setUsuario(usuario);

            resetTokenRepository.save(resetToken);
             // Devolver el ID del token y el token en texto plano
            return new ResetTokenResponseDto(resetTokenId, resetTokenNew);


        } else {
            throw new BadRequestException("Ya has pedido un token de recuperación. Intenta mas tarde");
        }
    }
}
