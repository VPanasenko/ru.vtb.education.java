package lesson7.Classes;

import lesson7.Annotations.AppColumn;
import lesson7.Annotations.AppTable;

@AppTable
public class Giraffe extends SQLClass{
    @AppColumn
    public int neckLength = (int)(Math.random() * 10);

    public String Name = "Giraffe" + neckLength;

    public int score = neckLength * 3;
}
