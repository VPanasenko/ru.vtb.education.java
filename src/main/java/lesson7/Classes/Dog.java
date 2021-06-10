package lesson7.Classes;

import lesson7.Annotations.AppTable;

@AppTable
public class Dog implements SQLClass{
    public String Name = "Dog" + (int)Math.random();

    public int score = (int)Math.pow(Math.random(), 2);
}
