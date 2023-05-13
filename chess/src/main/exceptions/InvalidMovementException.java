package main.exceptions;

public class InvalidMovementException extends RuntimeException {
    public InvalidMovementException(String msg) {
        super(msg);
    }
}
