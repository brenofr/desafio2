package br.com.dock.desafio2.adapter.rest.exception;

import br.com.dock.desafio2.application.exception.BusinessException;
import br.com.dock.desafio2.application.exception.DatastoreException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String CONST_MESSAGE_1 = "As entidades envolvidas na operacao nao sao validas";
    public static final String CONST_MESSAGE_2 = "Os dados fornecidos na entrada nao sao validos";

    @ExceptionHandler(value = {DatastoreException.class})
    protected ResponseEntity<Object> handleInvalidEntities(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = CONST_MESSAGE_1;

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleInvalidInputs(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = CONST_MESSAGE_1;

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}