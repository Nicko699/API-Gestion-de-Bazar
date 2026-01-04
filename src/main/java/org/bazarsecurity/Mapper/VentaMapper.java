package org.bazarsecurity.Mapper;

import org.bazarsecurity.Dto.Venta.VentaResponseDto;
import org.bazarsecurity.Dto.Venta.VentaResponseProductoDto;
import org.bazarsecurity.Dto.Venta.VentaTop5ResponseDto;
import org.bazarsecurity.Model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
// Mapper para convertir entre entidades de Venta y sus DTOs correspondientes
@Mapper(componentModel = "spring")
public interface VentaMapper {

    VentaResponseDto ventaToVentaResponseDto(Venta venta);

    @Mapping(source = "id", target = "ventaId")
    @Mapping(source = "listadetalleVentas", target = "listadetalleVentas")
    VentaResponseProductoDto ventaToVentaResponseProductoDto(Venta venta);

   @Mapping(source = "usuario", target = "usuarioTop5ResponseDto")
    VentaTop5ResponseDto ventaToVentaTop5ResponseDto(Venta venta);
}
