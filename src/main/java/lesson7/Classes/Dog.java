package lesson7.Classes;

import lesson7.Annotations.AppTable;

@AppTable
public class Dog extends SQLClass {
    public String Name = "Dog" + (int) (Math.random() * 37);

    public int score = (int) Math.pow(Math.random() *22, 2);
}
