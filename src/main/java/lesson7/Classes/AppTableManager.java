package lesson7.Classes;

public interface AppTableManager<T extends SQLClass> {
    void createTable();

    void save(T obj);

    void read();
}