package com.ajkko.userservice.exception;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
