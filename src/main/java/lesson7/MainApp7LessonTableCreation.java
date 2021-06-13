package lesson7;

import lesson7.Classes.*;

public class MainApp7LessonTableCreation {
    public static void main(String[] args) {
        AppTableManager tableManagerCat = new AppTableSQLLite<Cat>(Cat.class);
        tableManagerCat.createTable();
        tableManagerCat.save(new Cat());
        tableManagerCat.read();

        Dog dog = new Dog();

        AppTableManager tableManagerDog = new AppTableSQLLite<Dog>(Dog.class);
        tableManagerDog.createTable();
        tableManagerDog.save(new Dog());
        tableManagerDog.read();



        AppTableManager tableManagerGiraffe = new AppTableSQLLite<Giraffe>(Giraffe.class);
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
