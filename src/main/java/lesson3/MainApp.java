package lesson3;

import lesson3.Task3.Box;
import lesson3.Task3.Fruits.Apple;
import lesson3.Task3.Fruits.Fruit;
import lesson3.Task3.Fruits.Orange;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        String[] task1 = Task1(new String[]{"A", "AA", "AAA", "AAAA", "AAAAA", "AAAAAAA"}, 2, 5);
        System.out.println("Task1: " + task1[2]);
        ArrayList<String> task2 = Task2(new String[]{"A", "AA", "AAA", "AAAA", "AAAAA", "AAAAAAA"});
        System.out.println("Task2: " + task2);
        boolean comparedTask3 = Task3Compared();
        System.out.println("Task3 compared: " + comparedTask3);
        ArrayList<Fruit> withMovedTask3 = Task3Moved();
        System.out.println("Task3 moved: " + withMovedTask3);
        HashMap<String, Integer> hMapTask4 = Task4();
        System.out.println("Task4: " + hMapTask4);
    }

    public static <T> T[] Task1(T[] array, int firstIndex, int secondIndex) {
        if (array != null && secondIndex - firstIndex > 0 && array.length - 1 >= secondIndex) {

            T firstValue = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = firstValue;

            return array;
        }

        return array;
    }

    public static <T> ArrayList<T> Task2(T[] array) {
        if (array != null && array.length > 0) {
            ArrayList<T> arrayList = new ArrayList<>(array.length);

            for (T arValue : array) {
                arrayList.add(arValue);
            }

            return arrayList;
        }

        return new ArrayList<>();
    }

    public static boolean Task3Compared() {
        Box<Apple> boxApple = new Box<Apple>();
        Box<Orange> boxOrange = new Box<Orange>(new Orange());

        boxApple.add(new Apple());

        return boxApple.compare(boxOrange);
    }

    public static ArrayList<Fruit> Task3Moved() {
        Box<Apple> boxApple1 = new Box<Apple>();
        List<Apple> apples = Arrays.asList(new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple());
        Box<Apple> boxApple2 = new Box<Apple>(apples);

        ArrayList<Fruit> result = new ArrayList<>(boxApple1.moveAllInto(boxApple2));

        return result;
    }

    public static HashMap<String, Integer> Task4(){
        String[] words = {
                "One",
                "Two",
                "Three",
                "Four",
                "Five",
                "Six",
                "Seven",
                "Eight",
                "Nine",
                "Ten",
                "One",
                "Two",
                "Three",
                "Four",
                "Four"
        };

        List<String> wordsList = Arrays.asList(words);
        HashSet<String> uniqueWords = new HashSet<>(wordsList);
        HashMap<String, Integer> hMap = new HashMap<String, Integer>();

        for (String key: uniqueWords){
            hMap.put(key,Collections.frequency(wordsList, key));
        }

        return hMap;
    }
}
