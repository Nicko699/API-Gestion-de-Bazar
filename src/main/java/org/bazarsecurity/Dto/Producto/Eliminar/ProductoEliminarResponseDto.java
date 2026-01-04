package org.bazarsecurity.Dto.Producto.Eliminar;
// DTO para representar la respuesta de la eliminación de un producto
public class ProductoEliminarResponseDto {
    private String mensaje;

    public ProductoEliminarResponseDto() {
    }

    public ProductoEliminarResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
