package org.bazarsecurity.Exception;
// Excepción personalizada para recursos no encontrados (404 Not Found)
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
