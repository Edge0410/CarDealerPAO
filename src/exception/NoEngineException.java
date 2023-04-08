package exception;

public class NoEngineException extends IllegalStateException{
    public NoEngineException(String message){
        super(message);
    }
}
