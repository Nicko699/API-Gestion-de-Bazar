package org.bazarsecurity.Dto.Usuario.Crear;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
// DTO para representar una solicitud de creación de cuenta de usuario
public class UsuarioCrearCuentaRequestDto {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotEmpty(message = "El apellido no puede estar vacio")
    private String apellido;
    @NotEmpty(message = "El email no puede estar vacio")
    private String email;
    @NotEmpty(message = "La contraseña no puede estar vacia")
    private String password;
    @NotEmpty(message = "Ingrese por lo menos 1 rol.")
    private List<Long> listaRol;

    public UsuarioCrearCuentaRequestDto() {
    }

    public UsuarioCrearCuentaRequestDto(String nombre, String apellido, String email, String password, List<Long> listaRol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.listaRol = listaRol;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Long> listaRol) {
        this.listaRol = listaRol;
    }
}
