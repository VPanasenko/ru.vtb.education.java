package lesson11.model.DTO;

import lesson11.model.Order;
import lesson11.model.OrderDetails;
import lesson11.model.User;
import lombok.Data;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class OrderDTO {
    private Long id;
    private Date date;
    private Long user;
    private Set<Long> orderDetails;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.date = order.getDate();
        this.user = order.getUser().getId();
        this.orderDetails = order.getOrderDetails().stream().map(x -> x.getId()).collect(Collectors.toSet());
    }

    public OrderDTO(Optional<Order> orderOptional){
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            this.id = order.getId();
            this.date = order.getDate();
            this.user = order.getUser().getId();
            this.orderDetails = order.getOrderDetails().stream().map(x -> x.getId()).collect(Collectors.toSet());
        }
    }

    public OrderDTO(Long id, Date date, User user, Set<OrderDetails> orderDetails){
        this.id = id;
        this.date = date;
        this.user = user.getId();
        this.orderDetails = orderDetails.stream().map(x -> x.getId()).collect(Collectors.toSet());
    }
}
