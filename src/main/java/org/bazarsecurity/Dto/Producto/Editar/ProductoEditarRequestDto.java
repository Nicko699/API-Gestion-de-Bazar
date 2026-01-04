package org.bazarsecurity.Dto.Producto.Editar;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// DTO para representar una solicitud de edición de producto
public class ProductoEditarRequestDto {
   @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
   @NotBlank(message = "La marca es obligatoria")
    private String marca;
   @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;
   @NotNull(message = "El precio es obligatorio")
    private Double precio;
   @NotNull(message = "la cantidad es obligatoria")
    private Integer cantidadDisponible;
   @NotNull(message = "Activo es obligatorio")
    private Boolean activo;

    public ProductoEditarRequestDto() {
    }

    public ProductoEditarRequestDto(String nombre, String marca, String descripcion, Double precio, Integer cantidadDisponible, Boolean activo) {
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.activo = activo;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
