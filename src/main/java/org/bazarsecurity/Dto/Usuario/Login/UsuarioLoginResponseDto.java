package org.bazarsecurity.Dto.Usuario.Login;

import org.bazarsecurity.Dto.RefreshToken.RefreshTokenResponseDto;
// DTO para representar la respuesta de un inicio de sesión de usuario
public class UsuarioLoginResponseDto {
    private String acessToken;
    private String typeToken;
    private RefreshTokenResponseDto refreshToken;

    public UsuarioLoginResponseDto() {
    }

    public UsuarioLoginResponseDto(String acessToken, String typeToken, RefreshTokenResponseDto refreshToken) {
        this.acessToken = acessToken;
        this.typeToken = typeToken;
        this.refreshToken = refreshToken;
    }

    public String getAcessToken() {
        return acessToken;
    }

    public void setAcessToken(String acessToken) {
        this.acessToken = acessToken;
    }

    public String getTypeToken() {
        return typeToken;
    }

    public void setTypeToken(String typeToken) {
        this.typeToken = typeToken;
    }

    public RefreshTokenResponseDto getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshTokenResponseDto refreshToken) {
        this.refreshToken = refreshToken;
    }
}
