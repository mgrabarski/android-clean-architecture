package mateusz.grabarski.cleanarchitecture.domain.models.exceptions;

public class ValidationMessageException extends RuntimeException {

    public ValidationMessageException(String message) {
        super(message);
    }
}
