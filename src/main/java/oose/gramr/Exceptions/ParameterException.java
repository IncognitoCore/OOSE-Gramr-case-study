package oose.gramr.Exceptions;

/**
 * Custom exception to catch wrongly used parameters
 */
public class ParameterException extends Exception {
    public ParameterException(String message){
        super(message);
    }
}
