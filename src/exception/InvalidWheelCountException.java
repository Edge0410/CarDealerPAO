package exception;

public class InvalidWheelCountException extends RuntimeException{
    public InvalidWheelCountException(String message){
        super(message);
    }
}
