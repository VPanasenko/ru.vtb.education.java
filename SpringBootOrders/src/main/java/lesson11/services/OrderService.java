package lesson11.services;

import lesson11.model.DTO.OrderDTO;
import lesson11.model.Order;
import lesson11.repositories.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAllDTO();
    }

    public OrderDTO createOrder(Order order) {
        Order o = orderRepository.save(order);
        return new OrderDTO(o);
    }
}
