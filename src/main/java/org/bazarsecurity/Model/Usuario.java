package org.bazarsecurity.Model;

import jakarta.persistence.*;

import java.util.List;

//Creamos la entidad usuario
@Entity
public class Usuario {
    //Creamos los atributos que va a tener la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(name = "correo")
    private String email;
    private String password;
    @Column(name = "activo")
    private boolean active;

    // Relación N a N con la entidad Rol
    // Un usuario puede tener varios roles y cada rol puede pertenecer a varios usuarios
    // Usuario es la clase dueña de la relación
    @ManyToMany
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> listaRol;

    //Realcion 1 a N con usuario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RefreshToken> refreshTokens;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ResetToken resetToken;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Venta>listaVentas;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, String email, String password, boolean active, List<Rol> listaRol, List<RefreshToken> refreshTokens, ResetToken resetToken, List<Venta> listaVentas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.active = active;
        this.listaRol = listaRol;
        this.refreshTokens = refreshTokens;
        this.resetToken = resetToken;
        this.listaVentas = listaVentas;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public List<RefreshToken> getRefreshTokens() {
        return refreshTokens;
    }

    public void setRefreshTokens(List<RefreshToken> refreshTokens) {
        this.refreshTokens = refreshTokens;
    }

    public ResetToken getResetToken() {
        return resetToken;
    }

    public void setResetToken(ResetToken resetToken) {
        this.resetToken = resetToken;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }
}
