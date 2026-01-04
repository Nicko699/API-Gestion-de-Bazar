package org.bazarsecurity.Dto.ResetToken;

import java.time.LocalDateTime;
// DTO para representar la respuesta de un mensaje relacionado con el token de reseteo
public class ResetTokenMensajeResponseDto {

    private String mensaje;
    private String status;
    private LocalDateTime timestamp;

    public ResetTokenMensajeResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public ResetTokenMensajeResponseDto(String mensaje, String status, LocalDateTime timestamp) {
        this.mensaje = mensaje;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
