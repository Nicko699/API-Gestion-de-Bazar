package org.bazarsecurity.Dto.Producto.Crear;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
// DTO para representar una solicitud de creación de producto
public class ProductoCrearRequestDto {
    @NotEmpty(message = "El nombre del producto no puede estar vacío")
    private String nombre;
    @NotEmpty(message = "La marca del producto no puede estar vacía")
    private String marca;
    @NotEmpty(message = "La descripción del producto no puede estar vacía")
    private String descripcion;
    @NotNull(message = "El precio del producto no puede estar vacío")
    private double precio;
    @NotNull(message = "La cantidad del producto no puede estar vacía")
    private int cantidadDisponible;


    public ProductoCrearRequestDto(String nombre, String marca, String descripcion, double precio, int cantidadDisponible) {
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
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
