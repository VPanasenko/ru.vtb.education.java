package lesson2;

import lesson2.Array44.Array44;
import lesson2.Big.BigArrayList;
import lesson2.Big.BigLinkedList;
import lesson2.Exceptions.MyArrayDataException;
import lesson2.Exceptions.MyArraySizeException;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("==================================");
        System.out.println("Пробежка с некорректными значениям int в массиве");
        System.out.println("==================================");
        System.out.println("==================================");
        Tasks1To3(true);

        System.out.println("==================================");
        System.out.println("==================================");
        System.out.println("Пробежка только с цифрами в массиве");
        System.out.println("==================================");
        System.out.println("==================================");
        Tasks1To3(false);

        Task4();
        Task5();
    }

    private static void Tasks1To3(boolean withDataException){
        System.out.println("==================================");
        for (int i = 1; i < 6; i++){
            for (int j = 1; j < 6; j++){
                try {
                    String[][] arr = new String[i][j];
                    Array44 array44 = new Array44(arr, withDataException);
                    int sum = array44.SumArray();
                    if (array44.getArrayProcessCode() == "") {
                        System.out.println("Сумма по элементам массива: " + sum);
                    }
                }
                catch(MyArraySizeException | MyArrayDataException ex){
                    String errText = ex.getMessage();
                    System.out.println(errText);
                }
                catch(Exception ex){
                    String errText = "Произошла неизвестная ошибка с сообщением: " + ex.getMessage();
                    System.out.println(errText);
                }
                finally {
                    System.out.println("==================================");
                }
            }
        }
    }

    private static void Task4(){
        // Очень странные цифры получаются
        int[] testArray = new int[] {10, 100, 1000, 10000, 100000, 1000000, 10000000};
        int numberOfRepeats = 10000;
        String executeTimeTextArrayTemp = "Время доступа к медианному элементу на примере ArrayList на [arrayDimension] элементов равно 'arrayDimension' милисекунд.";

        for (int testValue: testArray) {
            BigArrayList bigArrayList = new BigArrayList(testValue);
            long time = bigArrayList.calculateAccessToMediate(numberOfRepeats);
            String executeTimeTextArray = executeTimeTextArrayTemp;

            executeTimeTextArray = executeTimeTextArray.replace("[arrayDimension]", Integer.toString(testValue));
            executeTimeTextArray = executeTimeTextArray.replace("arrayDimension", Long.toString(time));

            System.out.println(executeTimeTextArray);
        }

        System.out.println("==================================");

        String executeTimeTextLinkedTemp = "Время доступа к медианному элементу на примере LinkedList на [arrayDimension] элементов равно 'arrayDimension' милисекунд.";

        for (int testValue: testArray) {
            BigLinkedList bigLinkedList = new BigLinkedList(testValue);
            long time = bigLinkedList.calculateAccessToMediate(numberOfRepeats);
            String executeTimeTextLinked = executeTimeTextLinkedTemp;

            executeTimeTextLinked = executeTimeTextLinked.replace("[arrayDimension]", Integer.toString(testValue));
            executeTimeTextLinked = executeTimeTextLinked.replace("arrayDimension", Long.toString(time));

            System.out.println(executeTimeTextLinked);
        }
    }

    private static void Task5(){
        // Очень странные цифры получаются
        int[] testArray = new int[] {10, 100, 1000, 10000, 100000};
        int numberOfRepeats = 10000;
        String executeTimeTextArrayTemp = "Время удаления половины всех элементов из середины на примере ArrayList на [arrayDimension] элементов равно 'arrayDimension' милисекунд.";

        for (int testValue: testArray) {
            BigArrayList bigArrayList = new BigArrayList(testValue);
            long time = bigArrayList.removeHalfOfItems();
            String executeTimeTextArray = executeTimeTextArrayTemp;

            executeTimeTextArray = executeTimeTextArray.replace("[arrayDimension]", Integer.toString(testValue));
            executeTimeTextArray = executeTimeTextArray.replace("arrayDimension", Long.toString(time));

            System.out.println(executeTimeTextArray);
        }

        System.out.println("==================================");

        String executeTimeTextLinkedTemp = "Время удаления половины всех элементов из середины на примере LinkedList на [arrayDimension] элементов равно 'arrayDimension' милисекунд.";

        for (int testValue: testArray) {
            BigLinkedList bigLinkedList = new BigLinkedList(testValue);
            long time = bigLinkedList.removeHalfOfItems();
            String executeTimeTextLinked = executeTimeTextLinkedTemp;

            executeTimeTextLinked = executeTimeTextLinked.replace("[arrayDimension]", Integer.toString(testValue));
            executeTimeTextLinked = executeTimeTextLinked.replace("arrayDimension", Long.toString(time));

            System.out.println(executeTimeTextLinked);
        }
    }
}
