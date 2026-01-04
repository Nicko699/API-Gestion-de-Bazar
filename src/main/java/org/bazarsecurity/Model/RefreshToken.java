package org.bazarsecurity.Model;

import jakarta.persistence.*;

import java.time.Instant;

//Creamos la entidad refreshToken
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refreshTokenId;
    private String refreshTokenEncoded;
    private Instant fechaCreacion;
    private Instant fechaExpiracion;
    private boolean valido;
    //Relacion N a 1 con usuario
    @ManyToOne
    private Usuario usuario;

    public RefreshToken() {
    }

    public RefreshToken(Long id, String refreshTokenId, String refreshTokenEncoded, Instant fechaCreacion, Instant fechaExpiracion, boolean valido, Usuario usuario) {
        this.id = id;
        this.refreshTokenId = refreshTokenId;
        this.refreshTokenEncoded = refreshTokenEncoded;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.valido = valido;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getRefreshTokenEncoded() {
        return refreshTokenEncoded;
    }

    public void setRefreshTokenEncoded(String refreshTokenEncoded) {
        this.refreshTokenEncoded = refreshTokenEncoded;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Instant fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
