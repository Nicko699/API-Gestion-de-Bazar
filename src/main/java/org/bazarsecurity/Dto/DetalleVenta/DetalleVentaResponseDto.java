package org.bazarsecurity.Dto.DetalleVenta;
// DTO para representar la respuesta de un detalle de venta
public class DetalleVentaResponseDto {

    private Long id;
    private String nombreProducto;
    private String marcaProducto;
    private double precioUnitario;
    private int cantidad;
    private double subTotal;

    public DetalleVentaResponseDto() {
    }

    public DetalleVentaResponseDto(Long id, String nombreProducto, String marcaProducto, double precioUnitario, int cantidad, double subTotal) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
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
}
