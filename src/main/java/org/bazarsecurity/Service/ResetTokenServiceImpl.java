package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.ResetToken.ResetTokenCambiarPasswordRequestDto;
import org.bazarsecurity.Dto.ResetToken.ResetTokenRecuperarPasswordRequestDto;
import org.bazarsecurity.Dto.ResetToken.ResetTokenMensajeResponseDto;
import org.bazarsecurity.Dto.ResetToken.ResetTokenResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Model.ResetToken;
import org.bazarsecurity.Model.Usuario;
import org.bazarsecurity.Repository.ResetTokenRepository;
import org.bazarsecurity.Repository.UsuarioRepository;
import org.bazarsecurity.Security.TokenUtils.ResetTokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
// Implementación del servicio para gestionar las operaciones relacionadas con tokens de reseteo de contraseña
@Service
public class ResetTokenServiceImpl implements ResetTokenService {

    private final UsuarioRepository usuarioRepository;

    private final JavaMailSender javaMailSender;

    private final ResetTokenUtils resetTokenUtils;

    private final ResetTokenRepository resetTokenRepository;

    private final PasswordEncoder passwordEncoder;
// Correo electrónico desde el cual se enviarán los mensajes
    @Value("${spring.mail.username}")
    private String email;

    public ResetTokenServiceImpl(UsuarioRepository usuarioRepository, JavaMailSender javaMailSender, ResetTokenUtils resetTokenUtils, ResetTokenRepository resetTokenRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.javaMailSender = javaMailSender;
        this.resetTokenUtils = resetTokenUtils;
        this.resetTokenRepository = resetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }
// Método para iniciar el proceso de recuperación de contraseña
    @Transactional
    @Override
    public ResetTokenMensajeResponseDto RecuperarPassword(ResetTokenRecuperarPasswordRequestDto passwordRequest) throws NotFoundException, BadRequestException {

         //Encontramos el usuario en la bd
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByEmail(passwordRequest.getEmail());
        //Verificamos que el usuario exista
        if (usuario.isPresent()) {

            Usuario usuarioGet = usuario.get();

            ResetTokenResponseDto tokenResponseDto = resetTokenUtils.crearResetToken(usuarioGet);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(email);
            simpleMailMessage.setTo(usuarioGet.getEmail());
            simpleMailMessage.setSubject("Restablecimiento de contraseña");
            simpleMailMessage.setText(
                    "Hola " + usuarioGet.getEmail() + ",\n\n" +
                            "Hemos recibido una solicitud para restablecer tu contraseña.\n\n" +
                            "🔑 ID del Código: " + tokenResponseDto.getResetTokenId() + "\n" +
                            "🔒 Código: " + tokenResponseDto.getResetToken() + "\n\n" +
                            "Escriba únicamente este código en un sitio web o aplicación oficial. " +
                            "No lo comparta con nadie. Nunca lo pediremos fuera de una plataforma oficial.\n\n" +
                            "⚠ Por favor, no responda a este correo electrónico. Este mensaje ha sido enviado automáticamente " +
                            "y no se monitorea esta bandeja de entrada. Si necesita asistencia, contacte a nuestro equipo de soporte.\n\n" +
                            "Gracias por su atención.");

            javaMailSender.send(simpleMailMessage);

            return new ResetTokenMensajeResponseDto("El código de reestablecimiento de contraseña ha sido enviado a su correo electronico", "Ok", LocalDateTime.now());


        } else {
            throw new NotFoundException("El correo electronico ingresado no se encuentra registrado en nuestro sistema. Por favor ingresa uno valido");
        }

    }
// Método para cambiar la contraseña utilizando un token de reseteo
    @Transactional
    @Override
    public ResetTokenMensajeResponseDto cambiarPassword(ResetTokenCambiarPasswordRequestDto cambiarPasswordRequestDto) throws NotFoundException, BadRequestException {

        Optional<ResetToken> resetToken = resetTokenRepository.findResetTokenByResetTokenId(cambiarPasswordRequestDto.getResetTokenId());

        if (resetToken.isPresent()) {

            ResetToken resetTokenGet = resetToken.get();
// Verificamos si el token de reseteo coincide, es válido y no ha expirado
            boolean coincide = passwordEncoder.matches(cambiarPasswordRequestDto.getResetToken(), resetTokenGet.getResetTokenEncoded());
        // Si todas las condiciones se cumplen, cambiamos la contraseña del usuario
            if (coincide && resetTokenGet.isValido() && resetTokenGet.getFechaExpiracion().isAfter(Instant.now())) {

                Usuario usuario = resetTokenGet.getUsuario();

                String newPasswordEncoded = passwordEncoder.encode(cambiarPasswordRequestDto.getNewPassword());

                usuario.setPassword(newPasswordEncoded);

                usuarioRepository.save(usuario);

                resetTokenGet.setValido(false);

                resetTokenRepository.save(resetTokenGet);

                return new ResetTokenMensajeResponseDto("¡¡¡¡Cambio de contraseña exitoso!!!!", "Ok", LocalDateTime.now());


            } else {
                throw new BadRequestException("Token invalido o ha expirado");
            }

        } else {

            throw new NotFoundException("El reset Token ingresado no se encuentra en el sistema");
        }
    }
}
