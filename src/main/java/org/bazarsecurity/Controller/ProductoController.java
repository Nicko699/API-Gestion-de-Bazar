package org.bazarsecurity.Controller;

import jakarta.validation.Valid;
import org.bazarsecurity.Dto.Producto.Crear.ProductoCrearRequestDto;
import org.bazarsecurity.Dto.Producto.Crear.ProductoCrearResponseDto;
import org.bazarsecurity.Dto.Producto.Editar.ProductoEditarRequestDto;
import org.bazarsecurity.Dto.Producto.Editar.ProductoEditarResponseDto;
import org.bazarsecurity.Dto.Producto.Obtener.ProductoAdminResponseDto;
import org.bazarsecurity.Dto.Producto.Obtener.ProductoClienteResponseDto;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
// Controlador REST para la gestión de productos
@RestController
@RequestMapping("/producto")
public class ProductoController {
    // Servicio de productos inyectado
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    // Endpoint para obtener un producto para clientes
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/obtenerProducto/{id}")
    public ResponseEntity<ProductoClienteResponseDto> obtenerProductoCliente(@PathVariable Long id) throws NotFoundException{

        ProductoClienteResponseDto productoClienteResponseDto=productoService.obtenerProductoCliente(id);

        return ResponseEntity.ok(productoClienteResponseDto);
    }
    // Endpoint para obtener una lista paginada de productos para clientes
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/obtenerProductos")
    public ResponseEntity<Page<ProductoClienteResponseDto>> obtenerProductosCliente(Pageable pageable){

        Page<ProductoClienteResponseDto>productoClienteResponseDtos=productoService.obtenerProductosCliente(pageable);

        return ResponseEntity.ok(productoClienteResponseDtos);

    }
    // Endpoint para obtener un producto para administradores
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/obtenerProducto/{id}")
    public ResponseEntity<ProductoAdminResponseDto> obtenerProductoAdmin(@PathVariable Long id) throws NotFoundException{

        ProductoAdminResponseDto productoAdminResponseDto=productoService.obtenerProductoAdmin(id);

        return ResponseEntity.ok(productoAdminResponseDto);
    }
// Endpoint para obtener una lista paginada de productos para administradores
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/obtenerProductos")
    public ResponseEntity<Page<ProductoAdminResponseDto>> obtenerProductosAdmin(Pageable pageable){

        Page<ProductoAdminResponseDto>productoAdminResponseDtos=productoService.obtenerProductosAdmin(pageable);

        return ResponseEntity.ok(productoAdminResponseDtos);

    }
  // Endpoint para crear un nuevo producto
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearProducto")
    public ResponseEntity<ProductoCrearResponseDto> crearProducto(@Valid @RequestBody ProductoCrearRequestDto productoCrearRequestDto){

        ProductoCrearResponseDto productoCrearResponseDto=productoService.crearProducto(productoCrearRequestDto);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(productoCrearResponseDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(productoCrearResponseDto);

    }
   // Endpoint para editar un producto existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/editarProducto/{id}")
    public ResponseEntity<ProductoEditarResponseDto> editarProducto(@PathVariable  Long id,@Valid @RequestBody ProductoEditarRequestDto productoEditarRequestDto) throws NotFoundException{

        ProductoEditarResponseDto productoEditarResponseDto=productoService.editarProducto(id,productoEditarRequestDto);

        return ResponseEntity.ok(productoEditarResponseDto);
    }
  // Endpoint para desactivar un producto
   @PreAuthorize("hasRole('ADMIN')")
   @PatchMapping("/admin/desactivarProducto/{id}")
    public ResponseEntity<Map<String,String>>desactivarProducto(@PathVariable  Long id)throws NotFoundException{

        Map<String,String>mensaje=new HashMap<>();

        productoService.desactivarProducto(id);

        mensaje.put("mensaje","producto desactivado exitosamente");

        return ResponseEntity.ok(mensaje);

    }
  // Endpoint para eliminar un producto
    @DeleteMapping("/admin/eliminarProducto/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable  Long id) throws NotFoundException {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();

    }
  // Endpoint para obtener productos con cantidad menor a 5
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/obtenerProductosCantMenor5")
    public ResponseEntity< Page<ProductoAdminResponseDto>>obtenerProductosCant_Menor5(Pageable pageable){

    Page< ProductoAdminResponseDto >productoAdminResponseDtos=productoService.obtenerProductosCant_Menor5(pageable);

    return ResponseEntity.ok(productoAdminResponseDtos);
    }

}
