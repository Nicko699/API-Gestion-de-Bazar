package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.RefreshToken.RefreshTokenResponseDto;
import org.bazarsecurity.Dto.RefreshToken.RenovarTokenRequestDto;
import org.bazarsecurity.Dto.RefreshToken.RenovarTokenResponseDto;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Model.RefreshToken;
import org.bazarsecurity.Model.Usuario;
import org.bazarsecurity.Repository.RefreshTokenRepository;
import org.bazarsecurity.Security.TokenUtils.AcessTokenUtils;
import org.bazarsecurity.Security.TokenUtils.RefreshTokenUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
// Implementación del servicio para gestionar las operaciones relacionadas con tokens de refresco
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final AcessTokenUtils acessTokenUtils;

    private final RefreshTokenUtils refreshTokenUtils;


    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder, AcessTokenUtils acessTokenUtils, RefreshTokenUtils refreshTokenUtils) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.acessTokenUtils = acessTokenUtils;
        this.refreshTokenUtils = refreshTokenUtils;
    }
// Método para renovar el token de acceso utilizando un token de refresco
    @Transactional
    @Override
    public RenovarTokenResponseDto renovarAccessToken(RenovarTokenRequestDto renovarTokenRequestDto) throws NotFoundException, BadRequestException {

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findRefreshTokenByRefreshTokenId(renovarTokenRequestDto.getRefreshTokenId());

        if (refreshToken.isPresent()) {

            RefreshToken refreshTokenGet = refreshToken.get();
// Verificamos si el token de refresco coincide, es válido y no ha expirado
            boolean coincide = passwordEncoder.matches(renovarTokenRequestDto.getRefreshToken(), refreshTokenGet.getRefreshTokenEncoded());
// Si todas las condiciones se cumplen, generamos un nuevo token de acceso y un nuevo token de refresco
            if (coincide && refreshTokenGet.isValido() && !refreshTokenGet.getFechaExpiracion().isBefore(Instant.now())) {

                Usuario usuario = refreshTokenGet.getUsuario();
// Generamos un nuevo token de acceso
                String accessToken = acessTokenUtils.token(usuario.getEmail(), usuario.getListaRol());
// Creamos un nuevo token de refresco
                RefreshTokenResponseDto refreshTokenNew = refreshTokenUtils.crearRefreshToken(usuario.getEmail());
// Retornamos la respuesta con el nuevo token de acceso y el nuevo token de refresco
                return new RenovarTokenResponseDto(accessToken, "Bearer ", refreshTokenNew);
// Si el token de refresco no es válido o ha expirado, lanzamos una excepción
            } else {

                throw new BadRequestException("El Refresh Token no es válido o ha expirado");

            }
            // Si el token de refresco no se encuentra, lanzamos una excepción
        } else {
            throw new NotFoundException("El refresh Token ingresado no se encuentra en nuestro sistema");
        }
    }
}
