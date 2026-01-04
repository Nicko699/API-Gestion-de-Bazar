package org.bazarsecurity.Dto.Venta;

import org.bazarsecurity.Dto.Usuario.UsuarioTop5ResponseDto;

import java.time.LocalDateTime;
// DTO para representar una respuesta de las 5 mejores ventas
public class VentaTop5ResponseDto {
    private Long id;
    private LocalDateTime fechaVenta;
    private double totalVenta;
    private int cantidadProductos;
   private UsuarioTop5ResponseDto usuarioTop5ResponseDto;

    public VentaTop5ResponseDto() {
    }

    public VentaTop5ResponseDto(Long id, LocalDateTime fechaVenta, double totalVenta, int cantidadProductos, UsuarioTop5ResponseDto usuarioTop5ResponseDto) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.cantidadProductos = cantidadProductos;
        this.usuarioTop5ResponseDto = usuarioTop5ResponseDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public UsuarioTop5ResponseDto getUsuarioTop5ResponseDto() {
        return usuarioTop5ResponseDto;
    }

    public void setUsuarioTop5ResponseDto(UsuarioTop5ResponseDto usuarioTop5ResponseDto) {
        this.usuarioTop5ResponseDto = usuarioTop5ResponseDto;
    }
}
