package org.bazarsecurity.Dto.Usuario.Obtener;

import org.bazarsecurity.Dto.Rol.RolResponseDto;

import java.util.List;
// DTO para representar la respuesta de un usuario
public class UsuarioResponseDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private boolean active;
    private List<RolResponseDto> listaRol;

    public UsuarioResponseDto() {
    }

    public UsuarioResponseDto(Long id, String nombre, String apellido, String email, boolean active, List<RolResponseDto> listaRol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.active = active;
        this.listaRol = listaRol;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RolResponseDto> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<RolResponseDto> listaRol) {
        this.listaRol = listaRol;
    }
}
