package lesson1.Person;

import lesson1.Transport.Transportable;

public class Person {
    private String name = "Default Person";
    private Transportable transport;

    public Person() {}

    public Person(String _name)
    {
        name = _name;
    }

    public Person(String _name, Transportable _transport)
    {
        name = _name;
        transport = _transport;
    }

    public void ride()
    {
        if (transport != null) {
            System.out.println("Человек '" + name + "' начал передвигаться на транспорте типа '" + transport.getName() + "'");
        }
        System.out.println("У человека '" + name + "' не задан транспорт");
    }

    public void ride(Transportable _transport)
    {
        if (_transport != null) {
            this.stop();
            this.transport = _transport;
        }
        System.out.println("Человек '"+ name +"' начал передвигаться на транспорте типа '" + transport.getName() + "'");
    }

    public void stop()
    {
        System.out.println("Человек '"+ name +"' перестал передвигаться на транспорте типа '" + transport.getName() + "'");
    }
}
