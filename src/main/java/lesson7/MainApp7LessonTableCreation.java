package lesson7;

import lesson7.Classes.*;

public class MainApp7LessonTableCreation {
    public static void main(String[] args) {
        AppTableManager tableManagerCat = new AppTableSQLLite<SQLClass>(SQLClass.class);
        tableManagerCat.createTable();
        tableManagerCat.save(new Cat());
        tableManagerCat.read();

        AppTableManager tableManagerDog = new AppTableSQLLite<SQLClass>(SQLClass.class);
        tableManagerDog.createTable();
        tableManagerDog.save(new Dog());
        tableManagerDog.read();

        AppTableManager tableManagerGiraffe = new AppTableSQLLite<SQLClass>(SQLClass.class);
        tableManagerGiraffe.createTable();
        tableManagerGiraffe.save(new Giraffe());
        tableManagerGiraffe.read();

        // Нельзя, так как Elephant не имплементит SQLClass
//        AppTableManager tableManagerElephant = new AppTableSQLLite<SQLClass>(SQLClass.class);
//        tableManagerElephant.createTable();
//        tableManagerElephant.save(new Elephant());
//        tableManagerElephant.read();
    }
}
