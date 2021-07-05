package lesson11.services;

import lesson11.model.DTO.OrderDTO;
import lesson11.model.DTO.ProductDTO;
import lesson11.model.Order;
import lesson11.model.OrderDetails;
import lesson11.model.Product;
import lesson11.repositories.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsService orderDetailsService;
    private final ProductService productService;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAllDTO();
    }

    public OrderDTO createOrder(Order order) {
        Order o = orderRepository.save(order);
        for (OrderDetails orderDetails: order.getOrderDetails()){
            Optional<ProductDTO> pOptional = productService.getProductById(orderDetails.getProduct().getId());
            if (pOptional.isPresent()) {
                ProductDTO p = pOptional.get();
                OrderDetails od = new OrderDetails();
                od.setFixedPrice(p.getPrice());
                od.setAmount(orderDetails.getAmount());
                od.setOrder(o);
                od.setProduct(orderDetails.getProduct());
                orderDetailsService.createOrderDetails(od);
            }
        }
        return new OrderDTO(o);
    }
}
