package org.bazarsecurity.Exception;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
// Clase para representar un mensaje de error en las respuestas de la API
public class MensajeError {

    private int status;
    private HttpStatus error;
    private String mensaje;
    private LocalDateTime fecha;

    public MensajeError() {
    }

    public MensajeError(int status, HttpStatus error, String mensaje, LocalDateTime fecha) {
        this.status = status;
        this.error = error;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
