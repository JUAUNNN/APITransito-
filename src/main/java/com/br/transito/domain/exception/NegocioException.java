package com.br.transito.domain.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String message){
        super(message);
    }
}
