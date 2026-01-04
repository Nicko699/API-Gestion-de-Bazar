package org.bazarsecurity.Dto.Usuario;

import jakarta.validation.constraints.NotEmpty;
// DTO para representar una solicitud de reactivación de cuenta de usuario
public class UsuarioReactivarCuentaRequestDto {

    @NotEmpty(message = "El email es obligatorio")
    private String email;

    public UsuarioReactivarCuentaRequestDto() {
    }

    public UsuarioReactivarCuentaRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
