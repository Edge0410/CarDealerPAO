package exception;

public class InvalidSeatCountException extends RuntimeException{
    public InvalidSeatCountException(String message)
    {
        super(message);
    }
}
