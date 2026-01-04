package org.bazarsecurity.Exception;
// Excepción personalizada para solicitudes no autorizadas (401 Unauthorized)
public class UnauthorizedException extends Exception{
    public UnauthorizedException(String message) {
        super(message);
    }
}
