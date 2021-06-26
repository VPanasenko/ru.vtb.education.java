package lesson10.utils.exceptions;

public class IncorrectPriceException extends RuntimeException{
    public IncorrectPriceException(String message){
        super(message);
    }
}
