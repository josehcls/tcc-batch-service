package br.com.fermentis.tccrecipeservice.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends Exception {
    private String msg;
    private HttpStatus status;
}
