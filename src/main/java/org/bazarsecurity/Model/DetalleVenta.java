package org.bazarsecurity.Model;

import jakarta.persistence.*;
// Entidad que representa el detalle de una venta
@Entity
public class DetalleVenta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreProducto;
    private String marcaProducto;
    private double precioUnitario;
    private int cantidad;
    private double subTotal;

    @ManyToOne
    private Venta venta;

   @ManyToOne
    private Producto producto;

    public DetalleVenta() {
    }



    public DetalleVenta(Long id, String nombreProducto, String marcaProducto, double precioUnitario, int cantidad, double subTotal, org.bazarsecurity.Model.Venta venta, org.bazarsecurity.Model.Producto producto) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.venta = venta;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
