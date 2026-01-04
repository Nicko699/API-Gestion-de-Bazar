package org.bazarsecurity.Dto.ResetToken;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
// DTO para representar una solicitud de cambio de contraseña usando un token de reseteo
public class ResetTokenCambiarPasswordRequestDto {
    @NotEmpty(message = "El resetTokenId es obligatorio")
    private String resetTokenId;
    @NotEmpty(message = "El resetToken es obligatorio")
    private String resetToken;
    @NotEmpty(message = "La nueva contraseña no puede estar vacia")
    @Size(min = 6, message = "La contraseña debe tener minimo 6 caracteres")
    private String newPassword;

    public ResetTokenCambiarPasswordRequestDto() {
    }

    public ResetTokenCambiarPasswordRequestDto(String resetTokenId, String resetToken, String newPassword) {
        this.resetTokenId = resetTokenId;
        this.resetToken = resetToken;
        this.newPassword = newPassword;
    }

    public String getResetTokenId() {
        return resetTokenId;
    }

    public void setResetTokenId(String resetTokenId) {
        this.resetTokenId = resetTokenId;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
