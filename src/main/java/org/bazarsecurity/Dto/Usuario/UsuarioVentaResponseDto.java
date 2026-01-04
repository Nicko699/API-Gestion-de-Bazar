package org.bazarsecurity.Dto.Usuario;
// DTO para representar la respuesta de un usuario en una venta
public class UsuarioVentaResponseDto {
    private Long id;
    private String nombre;
    private String email;

    public UsuarioVentaResponseDto() {
    }

    public UsuarioVentaResponseDto(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
