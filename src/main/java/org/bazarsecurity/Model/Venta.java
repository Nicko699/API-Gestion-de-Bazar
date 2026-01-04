package org.bazarsecurity.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
// Entidad que representa una venta realizada en el sistema
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaVenta;
    private double totalVenta;
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta>listadetalleVentas;

    public Venta() {
    }

    public Venta(Long id, LocalDateTime fechaVenta, double totalVenta, Usuario usuario, List<DetalleVenta> listadetalleVentas) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.usuario = usuario;
        this.listadetalleVentas = listadetalleVentas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleVenta> getListadetalleVentas() {
        return listadetalleVentas;
    }

    public void setListadetalleVentas(List<DetalleVenta> listadetalleVentas) {
        this.listadetalleVentas = listadetalleVentas;
    }
}
