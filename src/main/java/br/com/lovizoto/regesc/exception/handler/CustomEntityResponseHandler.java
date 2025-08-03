package br.com.lovizoto.regesc.exception.handler;

import br.com.lovizoto.regesc.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomEntityResponseHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleException(Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //erro que vai dar caso a idade for menor que 18
    public final ResponseEntity<ExceptionResponse> handleValidationException(Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }



}
