package org.bazarsecurity.Dto.DetalleVenta;
// DTO para representar los detalles de un producto en una venta
public class DetalleVentaProductoResponseDto {

    private String nombreProducto;
    private String marcaProducto;
    private double precioUnitario;
    private int cantidad;

    public DetalleVentaProductoResponseDto() {
    }

    public DetalleVentaProductoResponseDto(String nombreProducto, String marcaProducto, double precioUnitario, int cantidad) {
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
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
}
