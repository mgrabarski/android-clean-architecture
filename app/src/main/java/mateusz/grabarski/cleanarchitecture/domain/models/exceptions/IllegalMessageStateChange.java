package mateusz.grabarski.cleanarchitecture.domain.models.exceptions;

public class IllegalMessageStateChange extends RuntimeException {

    public IllegalMessageStateChange(String message) {
        super(message);
    }
}
