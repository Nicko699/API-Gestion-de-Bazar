package org.bazarsecurity.Dto.Usuario.Crear;

import org.bazarsecurity.Dto.Rol.RolResponseDto;
import org.bazarsecurity.Model.Rol;

import java.util.List;
// DTO para representar una respuesta de creación de cuenta de usuario
public class UsuarioCrearCuentaResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private List<RolResponseDto> listaRol;

    public UsuarioCrearCuentaResponseDto() {
    }

    public UsuarioCrearCuentaResponseDto(Long id, String nombre, String apellido, String email, List<RolResponseDto> listaRol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
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

    public List<RolResponseDto> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<RolResponseDto> listaRol) {
        this.listaRol = listaRol;
    }
}
