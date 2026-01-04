package org.bazarsecurity.Dto.RefreshToken;

import jakarta.validation.constraints.NotEmpty;
// DTO para representar una solicitud de renovación de token de refresco
public class RenovarTokenRequestDto {
  @NotEmpty(message = "El refreshTokenId es obligatorio")
    private String refreshTokenId;
  @NotEmpty(message = "El refreshToken es obligatorio")
    private String refreshToken;

    public RenovarTokenRequestDto() {
    }

    public RenovarTokenRequestDto(String refreshTokenId, String refreshToken) {
        this.refreshTokenId = refreshTokenId;
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
