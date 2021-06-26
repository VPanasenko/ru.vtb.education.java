package lesson10.utils.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class AppError {
    private int statusCode;
    private String message;
    private Date timestamp;

    public AppError(int code, String m){
        statusCode = code;
        message = m;
        timestamp = new Date();
    }
}
