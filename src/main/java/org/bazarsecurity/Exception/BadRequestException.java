package org.bazarsecurity.Exception;
// Excepción personalizada para solicitudes incorrectas (400 Bad Request)
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
