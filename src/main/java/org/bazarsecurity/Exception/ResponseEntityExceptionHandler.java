package org.bazarsecurity.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Manejador global de excepciones para la aplicación
@ControllerAdvice
public class ResponseEntityExceptionHandler {
 
    private Map<String, String> mensaje;
  // Manejador para excepciones de tipo NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MensajeError> notFoundException(NotFoundException exception) {

        MensajeError mensajeError = new MensajeError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
    }

// Manejador para excepciones de validación de argumentos de método
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        Map<String, String> mensaje = new HashMap<>();
    
        // Obtener la lista de errores de validación
        List<FieldError> errorList = exception.getBindingResult().getFieldErrors();

        for (FieldError error : errorList) {

            String field = error.getField();

            String message = error.getDefaultMessage();

            mensaje.put(field, message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
    }
// Manejador para excepciones de tipo BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MensajeError> badRequest(BadRequestException exception) {

        MensajeError mensajeError = new MensajeError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }
// Manejador para excepciones de tipo UnauthorizedException
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<MensajeError>unauthorizedException(UnauthorizedException exception){

        MensajeError mensajeError=new MensajeError(HttpStatus.UNAUTHORIZED.value() ,HttpStatus.UNAUTHORIZED,exception.getMessage(),LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mensajeError);

    }
}
