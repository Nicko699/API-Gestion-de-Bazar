package org.bazarsecurity.Dto.Producto.Obtener;
// DTO para representar la respuesta de un producto para clientes
public class ProductoClienteResponseDto {

    private Long id;
    private String nombre;
    private String marca;
    private String descripcion;
    private double precio;
    private int cantidadDisponible;

    public ProductoClienteResponseDto() {
    }

    public ProductoClienteResponseDto(Long id, String nombre, String marca, String descripcion, double precio, int cantidadDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
