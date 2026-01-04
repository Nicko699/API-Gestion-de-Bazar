package org.bazarsecurity.Service;

import org.bazarsecurity.Dto.Venta.*;
import org.bazarsecurity.Exception.BadRequestException;
import org.bazarsecurity.Exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.List;
// Servicio para gestionar las operaciones relacionadas con ventas
public interface VentaService {

    public VentaResponseDto obtenerVentaCliente(Long id, UserDetails userDetails) throws NotFoundException;

    public VentaResponseDto obtenerVentaAdmin(Long id) throws NotFoundException;

    public Page<VentaResponseDto>obtenerVentasCliente(Pageable pageable, UserDetails userDetails);

    public Page<VentaResponseDto>obtenerVentasAdmin(Pageable pageable);

    public VentaResponseDto crearVenta(VentaCrearRequestDto ventaCrearRequestDto, UserDetails userDetails) throws NotFoundException, BadRequestException;

    public void eliminarVenta(Long id) throws NotFoundException;

    public VentaResponseProductoDto obtenerProductosPorVenta(Long id) throws NotFoundException;

    public VentasDiaResponseDto obtenerVentasPorFecha(LocalDate fecha) throws NotFoundException;

    public List<VentaTop5ResponseDto> obtenerTo5MayorVenta();


}
