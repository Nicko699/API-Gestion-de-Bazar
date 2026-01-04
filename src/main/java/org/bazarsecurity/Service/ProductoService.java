package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.Producto.Editar.ProductoEditarRequestDto;
import org.bazarsecurity.Dto.Producto.Editar.ProductoEditarResponseDto;
import org.bazarsecurity.Dto.Producto.Obtener.ProductoAdminResponseDto;
import org.bazarsecurity.Dto.Producto.Obtener.ProductoClienteResponseDto;
import org.bazarsecurity.Dto.Producto.Crear.ProductoCrearRequestDto;
import org.bazarsecurity.Dto.Producto.Crear.ProductoCrearResponseDto;
import org.bazarsecurity.Exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Servicio para gestionar las operaciones relacionadas con productos
public interface ProductoService {

    public ProductoClienteResponseDto obtenerProductoCliente(Long id) throws NotFoundException;

    public Page<ProductoClienteResponseDto> obtenerProductosCliente(Pageable pageable);

    public ProductoAdminResponseDto obtenerProductoAdmin(Long id) throws NotFoundException;

    public Page<ProductoAdminResponseDto> obtenerProductosAdmin(Pageable pageable);

    public ProductoCrearResponseDto crearProducto(ProductoCrearRequestDto productoCrearRequestDto);

    public ProductoEditarResponseDto editarProducto(Long id, ProductoEditarRequestDto productoEditarRequestDto) throws NotFoundException;

    public void desactivarProducto(Long id)throws NotFoundException;

    public void eliminarProducto(Long id)throws NotFoundException;

    public Page<ProductoAdminResponseDto>obtenerProductosCant_Menor5(Pageable pageable);

}
