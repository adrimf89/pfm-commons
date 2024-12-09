package com.adri.pfm.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RepositoryException extends RuntimeException{
    public RepositoryException(String message) {
        super(message);
    }
}
