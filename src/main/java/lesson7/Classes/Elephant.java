package lesson7.Classes;

import lesson7.Annotations.AppColumn;

public class Elephant {
    @AppColumn
    public int hobbyLength = (int)(Math.random() * 42);

    public String Name = "Elephant" + hobbyLength;

    public int score = hobbyLength / 2;
}
