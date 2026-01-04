package org.bazarsecurity.Dto.Venta;
import org.bazarsecurity.Dto.DetalleVenta.DetalleVentaProductoResponseDto;
import java.util.List;
// DTO para representar una respuesta de venta con detalles de productos
public class VentaResponseProductoDto {

    private Long ventaId;
    private List<DetalleVentaProductoResponseDto>listadetalleVentas;

    public VentaResponseProductoDto() {
    }

    public VentaResponseProductoDto(Long ventaId, List<DetalleVentaProductoResponseDto> listadetalleVentas) {
        this.ventaId = ventaId;
        this.listadetalleVentas = listadetalleVentas;
    }

    public Long getVentaId() {
        return ventaId;
    }

    public void setVentaId(Long ventaId) {
        this.ventaId = ventaId;
    }

    public List<DetalleVentaProductoResponseDto> getListadetalleVentas() {
        return listadetalleVentas;
    }

    public void setListadetalleVentas(List<DetalleVentaProductoResponseDto> listadetalleVentas) {
        this.listadetalleVentas = listadetalleVentas;
    }
}
