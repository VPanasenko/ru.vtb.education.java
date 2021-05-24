package lesson2.Array44;

import lesson2.Exceptions.MyArrayDataException;
import lesson2.Exceptions.MyArraySizeException;

import java.text.MessageFormat;

public class Array44 {
    private int firstDim = 0;
    private int secondDim = 0;

    private boolean withDataException = false;

    private String[][] array44;

    private String arrayProcessCode = "";

    public String getArrayProcessCode() {
        return arrayProcessCode;
    }

    public Array44(String[][] arr){
        array44 = arr;
    }

    public Array44(String[][] arr, boolean withStrValueInIntArray){
        array44 = arr;
        withDataException = withStrValueInIntArray;
    }

    public int SumArray() throws MyArraySizeException, MyArrayDataException {
        int sum = -1;
        CheckSize();
        FillArray(withDataException);
        sum = CalculateSum();
        return sum;
    }

    private void CheckSize(){
        try{
            if (array44 == null){
                throw new MyArraySizeException();
            }

            firstDim = array44.length;

            if (firstDim < 1){
                throw new MyArraySizeException();
            }

            secondDim = array44[0].length;

            if ((firstDim != 4) || (secondDim != 4)){
                throw new MyArraySizeException();
            }

            System.out.println("Успешная проверка массива!");
        }
        catch(MyArraySizeException ex){
            arrayProcessCode = ex.getCode();
            String errorText = "Ошибка вида MyArraySizeException";
            throw new MyArraySizeException(errorText);
        }
        finally {
            System.out.println(MessageFormat.format("Массив имеет размерность {0} на {1}",firstDim, secondDim));
        }
    }

    private void FillArray(boolean withString){
        int randomMax = firstDim - secondDim > 0 ? firstDim : secondDim;
        for (int i = 0; i < firstDim; i++){
            for (int j = 0; j < secondDim; j++){
                array44[i][j] = Integer.toString(getRandomNumber(0, randomMax));
            }
            if (withString){
                array44[getRandomNumber(0, firstDim)][getRandomNumber(0, secondDim)] = "Java";
            }
        }
    }

    private int CalculateSum(){
        int sum = 0;

        System.out.println("Базовая сумма: " + sum);

        int currentFirstDim = 0;
        int currentSecondDim = 0;

        try{
            if (array44 == null){
                throw new MyArraySizeException();
            }

            for (int i = 0; i < firstDim; i++){
                for (int j = 0; j < secondDim; j++){
                    currentFirstDim = i;
                    currentSecondDim = j;
                    String value = array44[i][j];
                    System.out.println(String.format("Ячейка [%1$s][%2$s]. Текущее значение: %3$s", currentFirstDim, currentSecondDim, value));
                    sum += Integer.parseInt(value);
                    System.out.println("Сумма с текущим значением: " + sum);
                }
            }
        }
        catch(MyArraySizeException ex){
            arrayProcessCode = ex.getCode();
            String errorText = String.format("Ошибка вида MyArraySizeException");
            throw new MyArraySizeException(errorText);
        }
        catch(NumberFormatException ex){
            // https://stackoverflow.com/questions/53984093/java-why-the-subclass-of-arithmeticexception-class-is-not-called

            String errorText = String.format("Ошибка вида MyArrayDataException в ячейке [%1$s][%2$s]", currentFirstDim, currentSecondDim);
            MyArrayDataException exception = new MyArrayDataException(errorText);
            arrayProcessCode = exception.getCode();
            throw  exception;
        }

        return sum;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
