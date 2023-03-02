package br.com.glafoxyquiz.domain.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
