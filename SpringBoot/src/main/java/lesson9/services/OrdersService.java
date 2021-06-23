package lesson9.services;

import lesson9.model.Order;
import lesson9.repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private Repository<Order> repository;

    public OrdersService() {
    }

//    public OrdersService(Repository repository) {
//        this.repository = repository;
//    }

    public List<Order> findAllOrders() {
        return repository.getAll();
    }

    public Order findOrder(long id) {
        return repository.get(id);
    }

    public void save(Order order) {
        repository.save(order);
    }
}
