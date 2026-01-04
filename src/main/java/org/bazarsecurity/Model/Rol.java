package org.bazarsecurity.Model;

import jakarta.persistence.*;
import java.util.List;

//Creamos la entidad rol
@Entity
public class Rol {
    //Creamos los atributos de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    // Relación N a N con la entidad Usuario
    // Un Rol puede tener varios Usuarios y cada Usuario puede pertenecer a varios Roles
    // Rol es solo la referencia de la relación con usuario
    @ManyToMany(mappedBy = "listaRol")
    private List<Usuario> listaUsuario;

    public Rol() {
    }

    public Rol(Long id, String nombre, List<Usuario> listaUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.listaUsuario = listaUsuario;
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

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
}
