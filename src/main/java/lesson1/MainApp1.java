package lesson1;

import lesson1.Person.Person;
import lesson1.Transport.*;

public class MainApp1 {
    public static void main(String[] args) {
        Transportable[] transports = {
                new Motorcycle(),
                new Car(),
                new Jet()
        };

        Person anyone = new Person("Victor", new Board());

        for (Transportable transport: transports){
            anyone.ride(transport);
        }
    }
}
