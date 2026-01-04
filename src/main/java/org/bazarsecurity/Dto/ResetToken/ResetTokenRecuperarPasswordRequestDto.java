package org.bazarsecurity.Dto.ResetToken;

import jakarta.validation.constraints.NotEmpty;
// DTO para representar una solicitud de recuperación de contraseña
public class ResetTokenRecuperarPasswordRequestDto {
    @NotEmpty(message = "El email es obligatorio")
    private String email;

    public ResetTokenRecuperarPasswordRequestDto() {
    }

    public ResetTokenRecuperarPasswordRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
