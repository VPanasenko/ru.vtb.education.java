package lesson11.services;

import lesson11.model.Order;
import lesson11.model.User;
import lesson11.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

//    public Optional<List<Order>> getOrdersByUser(long userId){
//        return orderRepository.getAllByUserId(userId);
//    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}
