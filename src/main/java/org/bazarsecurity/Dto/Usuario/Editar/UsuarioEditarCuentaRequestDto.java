package org.bazarsecurity.Dto.Usuario.Editar;
// DTO para representar una solicitud de edición de cuenta de usuario
public class UsuarioEditarCuentaRequestDto {

    private String nombre;
    private String apellido;

    public UsuarioEditarCuentaRequestDto() {
    }

    public UsuarioEditarCuentaRequestDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
