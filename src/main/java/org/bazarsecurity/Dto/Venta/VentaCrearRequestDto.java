package org.bazarsecurity.Dto.Venta;
import org.bazarsecurity.Dto.DetalleVenta.DetalleVentaRequestDto;

import java.util.List;
// DTO para representar una solicitud de creación de venta
public class VentaCrearRequestDto {

    private List<DetalleVentaRequestDto> listadetalleVentas;

    public VentaCrearRequestDto() {
    }

    public VentaCrearRequestDto(List<DetalleVentaRequestDto> listadetalleVentas) {
        this.listadetalleVentas = listadetalleVentas;
    }

    public List<DetalleVentaRequestDto> getListadetalleVentas() {
        return listadetalleVentas;
    }

    public void setListadetalleVentas(List<DetalleVentaRequestDto> listadetalleVentas) {
        this.listadetalleVentas = listadetalleVentas;
    }
}
