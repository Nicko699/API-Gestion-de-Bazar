package org.bazarsecurity.Dto.Usuario.Login;

import jakarta.validation.constraints.NotEmpty;
// DTO para representar una solicitud de inicio de sesión de usuario
public class UsuarioLoginRequestDto {
    @NotEmpty(message = "El email es obligatorio")
    private String email;
    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;

    public UsuarioLoginRequestDto() {
    }

    public UsuarioLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
