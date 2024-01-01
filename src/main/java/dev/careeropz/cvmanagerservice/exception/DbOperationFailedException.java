package dev.careeropz.cvmanagerservice.exception;

public class DbOperationFailedException extends RuntimeException {
    public DbOperationFailedException(String message) {
        super(message);
    }
}
