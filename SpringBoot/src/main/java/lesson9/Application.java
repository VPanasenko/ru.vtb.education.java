package lesson9;

import lesson9.model.Order;
import lesson9.repositories.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SessionFactory sessionFactory() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        return factory;
    }

    @Bean
    public Repository<Order> repository() {
        Repository<Order> repository = new Repository<>();
        repository.setClassToManage(Order.class);
        return repository;
    }
}
