package org.bazarsecurity.Dto.DetalleVenta;

import jakarta.validation.constraints.NotNull;
// DTO para representar una solicitud de detalle de venta
public class DetalleVentaRequestDto {
    @NotNull(message = "El id del producto no puede estar vacío")
    private Long productoId;
    @NotNull(message = "La cantidad no puede estar vacía")
    private int cantidad;

    public DetalleVentaRequestDto() {
    }

    public DetalleVentaRequestDto(Long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
