package betrayal.controllers;

public class EmptyDeckException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message.
     */
    public EmptyDeckException(String message) {
        super(message);
    }
}
