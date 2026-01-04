package org.bazarsecurity.Mapper;

import org.bazarsecurity.Dto.Producto.Editar.ProductoEditarRequestDto;
import org.bazarsecurity.Dto.Producto.Editar.ProductoEditarResponseDto;
import org.bazarsecurity.Dto.Producto.Obtener.ProductoAdminResponseDto;
import org.bazarsecurity.Dto.Producto.Obtener.ProductoClienteResponseDto;
import org.bazarsecurity.Dto.Producto.Crear.ProductoCrearRequestDto;
import org.bazarsecurity.Dto.Producto.Crear.ProductoCrearResponseDto;
import org.bazarsecurity.Model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
// Mapper para convertir entre entidades de Producto y sus DTOs correspondientes
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductoMapper {

    //Metodo get
    ProductoClienteResponseDto productoToProductoClienteResponseDto(Producto producto);

    ProductoAdminResponseDto productoToProductoAdminResponseDto(Producto producto);

    //Metodo Post
    ProductoCrearResponseDto productoToProductoCrearResponseDto(Producto producto);

    Producto productoCrearRequestDtoToProducto(ProductoCrearRequestDto productoCrearRequestDto);

    //Metodo Put/patch
    ProductoEditarResponseDto productoToProductoEditarResponseDto(Producto producto);

     
    Producto ProductoEditarRequestDtoToProducto ( ProductoEditarRequestDto productoEditarRequestDto,@MappingTarget Producto producto);


}
