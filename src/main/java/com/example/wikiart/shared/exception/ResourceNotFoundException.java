package com.example.wikiart.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    //ResourceNotFoundException tiene 4 formas(metodos), es polimorfismo, pero se comporta de manera diferente
    public ResourceNotFoundException(){ super();}

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(String.format("%s with id %d not found.", resourceName, resourceId));
    }
}
