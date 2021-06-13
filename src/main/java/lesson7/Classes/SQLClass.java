package lesson7.Classes;

import lesson7.Annotations.AppColumn;
import lesson7.Annotations.AppTable;

// Где лучше сделать анотацию? Здесь или в детях?
// Если здесь, то все дети автоматически подходят при создании таблицы в AppTableSQLLite
// @AppTable
public class SQLClass {

    // Почему-то потерял концепцию, как запретить потомкам переопределять поле.
    @AppColumn
    final int id = 0;

    @AppColumn
    String Name = "";

    @AppColumn
    int score = 0;
}
