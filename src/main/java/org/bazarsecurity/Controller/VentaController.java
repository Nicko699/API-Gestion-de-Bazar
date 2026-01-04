package org.bazarsecurity.Controller;
import jakarta.validation.Valid;
import org.bazarsecurity.Dto.Venta.*;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.bazarsecurity.Service.VentaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
// Controlador REST para la gestión de ventas
@RestController
@RequestMapping("/venta")
public class VentaController {
// Servicio de ventas inyectado
private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }
// Endpoint para obtener una venta para clientes
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/obtenerVenta/{id}")
    public ResponseEntity<VentaResponseDto> obtenerVentaCliente(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) throws NotFoundException{

        VentaResponseDto ventaResponseDto=ventaService.obtenerVentaCliente(id,userDetails);

        return  ResponseEntity.ok(ventaResponseDto);
    }
   // Endpoint para obtener una venta para administradores
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/obtenerVenta/{id}")
    public ResponseEntity<VentaResponseDto> obtenerVentaAdmin(@PathVariable Long id) throws NotFoundException{

        VentaResponseDto ventaResponseDto=ventaService.obtenerVentaAdmin(id);

        return ResponseEntity.ok(ventaResponseDto);

    }
// Endpoint para obtener una lista paginada de ventas para clientes
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/obtenerVentas")
    public ResponseEntity<Page<VentaResponseDto>> obtenerVentasCliente(Pageable pageable,@AuthenticationPrincipal UserDetails userDetails){

        Page<VentaResponseDto>ventasResponseDto=ventaService.obtenerVentasCliente(pageable,userDetails);

        return ResponseEntity.ok(ventasResponseDto);
    }
// Endpoint para obtener una lista paginada de ventas para administradores
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/obtenerVentas")
    public ResponseEntity<Page<VentaResponseDto>>obtenerVentasAdmin(Pageable pageable){

        Page<VentaResponseDto>ventasResponseDto=ventaService.obtenerVentasAdmin(pageable);

       return ResponseEntity.ok(ventasResponseDto);
    }
// Endpoint para crear una nueva venta
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/crearVenta")
    public ResponseEntity<VentaResponseDto> crearVenta(@RequestBody @Valid VentaCrearRequestDto ventaCrearRequestDto,@AuthenticationPrincipal UserDetails userDetails) throws NotFoundException, BadRequestException{

        VentaResponseDto ventaResponseDto=ventaService.crearVenta(ventaCrearRequestDto,userDetails);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ventaResponseDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(ventaResponseDto);
    }
// Endpoint para eliminar una venta por ID
    @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/admin/eliminarVenta/{id}")
    public ResponseEntity<Void >eliminarVenta(@PathVariable Long id) throws NotFoundException{

        ventaService.eliminarVenta(id);

        return ResponseEntity.noContent().build();
    }

// Endpoint para obtener los productos asociados a una venta por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerProductosPorVenta/{id}")
    public ResponseEntity<VentaResponseProductoDto> obtenerProductosPorVenta(@PathVariable Long id) throws NotFoundException{

        VentaResponseProductoDto ventaResponseProductoDto=ventaService.obtenerProductosPorVenta(id);

        return ResponseEntity.ok(ventaResponseProductoDto);
    }
// Endpoint para obtener las ventas realizadas en una fecha específica
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/obtenerVentasPorFecha/{fecha}")
    public ResponseEntity<VentasDiaResponseDto> obtenerVentasPorFecha(@PathVariable LocalDate fecha) throws NotFoundException{

        VentasDiaResponseDto ventasDiaResponseDto=ventaService.obtenerVentasPorFecha(fecha);

        return ResponseEntity.ok(ventasDiaResponseDto);
    }

// Endpoint para obtener el top 5 de mayores ventas
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/top5Ventas")
    public ResponseEntity< List<VentaTop5ResponseDto>> obtenerTo5MayorVenta(){

        List<VentaTop5ResponseDto>ventasTop5ResponseDto=ventaService.obtenerTo5MayorVenta();

      return ResponseEntity.ok(ventasTop5ResponseDto);
    }

}
