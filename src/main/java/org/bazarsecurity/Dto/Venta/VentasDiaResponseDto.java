package org.bazarsecurity.Dto.Venta;
import java.time.LocalDate;
// DTO para representar una respuesta de ventas diarias por fecha
public class VentasDiaResponseDto {


    private LocalDate fecha;
    private int cantidadVentas;
    private double totalVentas;

    public VentasDiaResponseDto() {
    }

    public VentasDiaResponseDto(LocalDate fecha, int cantidadVentas, double totalVentas) {
        this.fecha = fecha;
        this.cantidadVentas = cantidadVentas;
        this.totalVentas = totalVentas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }
}
