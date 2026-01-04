package org.bazarsecurity.Controller;
import jakarta.validation.Valid;
import org.bazarsecurity.Dto.ResetToken.ResetTokenCambiarPasswordRequestDto;
import org.bazarsecurity.Dto.ResetToken.ResetTokenMensajeResponseDto;
import org.bazarsecurity.Dto.ResetToken.ResetTokenRecuperarPasswordRequestDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Service.ResetTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// Controlador REST para la gestión de tokens de reseteo de contraseña
@RestController
@RequestMapping("/resetToken")
public class ResetTokenController {
   // Servicio de tokens de reseteo inyectado
    private final ResetTokenService resetTokenService;

    public ResetTokenController(ResetTokenService resetTokenService) {
        this.resetTokenService = resetTokenService;
    }
  // Endpoint para iniciar el proceso de recuperación de contraseña
    @PostMapping("/recuperarPassword")
    public ResponseEntity<ResetTokenMensajeResponseDto> recuperarPassword(@Valid @RequestBody ResetTokenRecuperarPasswordRequestDto recuperarPasswordRequestDto) throws NotFoundException, BadRequestException {

        ResetTokenMensajeResponseDto resetTokenMensajeResponse=resetTokenService.RecuperarPassword(recuperarPasswordRequestDto);

        return ResponseEntity.ok(resetTokenMensajeResponse);
    }
 // Endpoint para cambiar la contraseña utilizando el token de reseteo
    @PostMapping("/cambiarPassword")
    public ResponseEntity<ResetTokenMensajeResponseDto>cambiarPassword(@Valid @RequestBody  ResetTokenCambiarPasswordRequestDto cambiarPasswordRequestDto) throws NotFoundException, BadRequestException {

        ResetTokenMensajeResponseDto resetTokenMensajeResponse=resetTokenService.cambiarPassword(cambiarPasswordRequestDto);

        return ResponseEntity.ok(resetTokenMensajeResponse);
    }
}
