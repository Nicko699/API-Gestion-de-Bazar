package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.ResetToken.ResetTokenCambiarPasswordRequestDto;
import org.bazarsecurity.Dto.ResetToken.ResetTokenRecuperarPasswordRequestDto;
import org.bazarsecurity.Dto.ResetToken.ResetTokenMensajeResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
// Servicio para gestionar las operaciones relacionadas con tokens de reseteo de contraseña
public interface ResetTokenService {

    public ResetTokenMensajeResponseDto RecuperarPassword(ResetTokenRecuperarPasswordRequestDto passwordRequest) throws NotFoundException, BadRequestException;

    public ResetTokenMensajeResponseDto cambiarPassword(ResetTokenCambiarPasswordRequestDto cambiarPasswordRequestDto) throws NotFoundException,BadRequestException;
}
