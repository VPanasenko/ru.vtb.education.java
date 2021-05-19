package lesson2;

public class MyArrayDataException extends NumberFormatException{
    private final String errorCode = "MADE2";

    public MyArrayDataException(){
        super();
    }

    public MyArrayDataException(String exceptionText){
        super(exceptionText);
    }

    public String getCode(){
        return errorCode;
    }
}
