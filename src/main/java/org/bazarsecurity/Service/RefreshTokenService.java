package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.RefreshToken.RenovarTokenRequestDto;
import org.bazarsecurity.Dto.RefreshToken.RenovarTokenResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
// Servicio para gestionar las operaciones relacionadas con tokens de refresco
public interface RefreshTokenService {

    public RenovarTokenResponseDto renovarAccessToken(RenovarTokenRequestDto renovarTokenRequestDto) throws NotFoundException, BadRequestException;
}
