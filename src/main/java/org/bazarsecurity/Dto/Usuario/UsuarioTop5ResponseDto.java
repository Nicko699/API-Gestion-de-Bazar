package org.bazarsecurity.Dto.Usuario;
// DTO para representar la respuesta de un usuario de ventas en el top 5
public class UsuarioTop5ResponseDto {

    private String nombre;
    private String apellido;
    private String email;

    public UsuarioTop5ResponseDto() {
    }

    public UsuarioTop5ResponseDto(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
