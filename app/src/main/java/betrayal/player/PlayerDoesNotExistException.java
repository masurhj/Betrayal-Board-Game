package betrayal.player;

public class PlayerDoesNotExistException extends RuntimeException {

    /** Creates an instance of a PlayerDoesNotExistException.
     * @param errorMessage
     */
    public PlayerDoesNotExistException(final String errorMessage) {
        super(errorMessage);
    }
}
