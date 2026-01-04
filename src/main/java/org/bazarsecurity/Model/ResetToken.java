package org.bazarsecurity.Model;

import jakarta.persistence.*;

import java.time.Instant;
// Entidad que representa un token de restablecimiento de contraseña
@Entity
public class ResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resetTokenId;
    private String resetTokenEncoded;
    private Instant fechaCreacion;
    private Instant fechaExpiracion;
    private boolean valido;
    @OneToOne
    private Usuario usuario;

    public ResetToken() {
    }

    public ResetToken(Long id, String resetTokenId, String resetTokenEncoded, Instant fechaCreacion, Instant fechaExpiracion, boolean valido, Usuario usuario) {
        this.id = id;
        this.resetTokenId = resetTokenId;
        this.resetTokenEncoded = resetTokenEncoded;
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

    public String getResetTokenId() {
        return resetTokenId;
    }

    public void setResetTokenId(String resetTokenId) {
        this.resetTokenId = resetTokenId;
    }

    public String getResetTokenEncoded() {
        return resetTokenEncoded;
    }

    public void setResetTokenEncoded(String resetTokenEncoded) {
        this.resetTokenEncoded = resetTokenEncoded;
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
