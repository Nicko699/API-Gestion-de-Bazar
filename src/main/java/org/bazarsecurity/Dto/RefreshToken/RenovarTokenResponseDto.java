package org.bazarsecurity.Dto.RefreshToken;
// DTO para representar la respuesta de la renovación de un token
public class RenovarTokenResponseDto {
    private String acessToken;
    private String typeToken;
    private RefreshTokenResponseDto refreshToken;

    public RenovarTokenResponseDto() {
    }

    public RenovarTokenResponseDto(String acessToken, String typeToken, RefreshTokenResponseDto refreshToken) {
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
