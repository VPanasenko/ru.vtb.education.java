package lesson2;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException{
    private final String errorCode = "MASE2";

    public MyArraySizeException(){
        super();
    }

    public MyArraySizeException(String exceptionText){
        super(exceptionText);
    }

    public String getCode(){
        return errorCode;
    }
}
