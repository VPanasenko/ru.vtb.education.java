package lesson6;

public class Employee {
    private String Name;
    private int Age;
    private double Salary;

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public double getSalary() {
        return Salary;
    }

    public Employee(String name, int age, double salary) {
        Name = name;
        Age = age;
        Salary = salary;
    }
}
