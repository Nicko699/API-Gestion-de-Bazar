package org.bazarsecurity.Controller;

import jakarta.validation.Valid;
import org.bazarsecurity.Dto.RefreshToken.RenovarTokenRequestDto;
import org.bazarsecurity.Dto.RefreshToken.RenovarTokenResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// Controlador REST para la gestión de tokens de refresco
@RestController
@RequestMapping("/refreshToken")
public class RefreshTokenController {
// Servicio de tokens de refresco inyectado
    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }
  // Endpoint para renovar el token de acceso
    @PostMapping("/renovarAccessToken")
    public ResponseEntity<RenovarTokenResponseDto> renovarAccessToken(@Valid @RequestBody RenovarTokenRequestDto renovarTokenRequestDto) throws NotFoundException, BadRequestException {

        RenovarTokenResponseDto renovarTokenResponseDto=refreshTokenService.renovarAccessToken(renovarTokenRequestDto);

        return ResponseEntity.ok(renovarTokenResponseDto);

    }

}
