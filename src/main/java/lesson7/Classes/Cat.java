package lesson7.Classes;

import lesson7.Annotations.AppTable;

@AppTable
public class Cat implements SQLClass {
    public int tailLength = (int)(Math.random() * 100);

    public String Name = "Cat" + tailLength;

    public int score = tailLength * 3;
}
