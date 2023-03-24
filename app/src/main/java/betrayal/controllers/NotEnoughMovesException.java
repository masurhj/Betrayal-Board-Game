package betrayal.controllers;

public class NotEnoughMovesException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message.
     */
    public NotEnoughMovesException(String message) {
        super(message);
    }
}
