package org.bazarsecurity.Dto.Venta;
import org.bazarsecurity.Dto.DetalleVenta.DetalleVentaResponseDto;
import org.bazarsecurity.Dto.Usuario.UsuarioVentaResponseDto;
import java.time.LocalDateTime;
import java.util.List;
// DTO para representar una respuesta de venta
public class VentaResponseDto {

    private Long id;
    private LocalDateTime fechaVenta;
    private double totalVenta;
    private UsuarioVentaResponseDto usuario;
    private List<DetalleVentaResponseDto> listadetalleVentas;

    public VentaResponseDto() {
    }

    public VentaResponseDto(Long id, LocalDateTime fechaVenta, double totalVenta, UsuarioVentaResponseDto usuario, List<DetalleVentaResponseDto> listadetalleVentas) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.usuario = usuario;
        this.listadetalleVentas = listadetalleVentas;
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

    public UsuarioVentaResponseDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVentaResponseDto usuario) {
        this.usuario = usuario;
    }

    public List<DetalleVentaResponseDto> getListadetalleVentas() {
        return listadetalleVentas;
    }

    public void setListadetalleVentas(List<DetalleVentaResponseDto> listadetalleVentas) {
        this.listadetalleVentas = listadetalleVentas;
    }
}
