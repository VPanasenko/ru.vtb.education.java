package lesson7;

import lesson7.Annotations.AppColumn;
import lesson7.Annotations.AppTable;

@AppTable
public class AppClass {
    @AppColumn
    public String Name;
    @AppColumn
    public int Score;
}
