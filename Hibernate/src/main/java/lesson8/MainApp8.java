package lesson8;

import lesson8.Entities.Animal;
import lesson8.Entities.Student;
import lesson8.Entities.Vehicle;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp8 {
        public static void main(String[] args) {
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

            Repository<Student> studentRepository = new Repository(factory, Student.class);
            studentRepository.read(1L);
            Student studentVictor = studentRepository.get(1L);
            studentVictor.setScore(1000);
            studentRepository.save(studentVictor);
            studentRepository.read(1L);

            Repository<Animal> animalRepository = new Repository(factory, Animal.class);
            animalRepository.read(1L);
            Animal animalCat = animalRepository.get(1L);
            animalCat.setAge(42);
            animalRepository.save(animalCat);
            animalRepository.read(1L);

            Repository<Vehicle> vehicleRepository = new Repository(factory, Vehicle.class);
            vehicleRepository.read(1L);
            Vehicle vehicleNissan = vehicleRepository.get(1L);
            vehicleNissan.setModel("X-Trail");
            vehicleRepository.save(vehicleNissan);
            vehicleRepository.read(1L);
    }
}
