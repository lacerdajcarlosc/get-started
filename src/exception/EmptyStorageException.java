package exception;

public class EmptyStorageException extends RuntimeException{

    public EmptyStorageException(final String message) {
        super(message);
    }
}
