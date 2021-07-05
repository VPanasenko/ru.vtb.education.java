package lesson11.model.DTO;

import lesson11.model.Order;
import lesson11.model.OrderDetails;
import lesson11.model.Product;
import lesson11.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Optional;

@Data
public class OrderDetailsDTO {
    private Long id;
    private int amount;
    private Long order;
    private Long product;

    public OrderDetailsDTO(OrderDetails orderDetails){
        this.id = orderDetails.getId();
        this.amount = orderDetails.getAmount();
        this.order = orderDetails.getOrder().getId();
        this.product = orderDetails.getProduct().getId();
    }

    public OrderDetailsDTO(Optional<OrderDetails> orderDetailsOptional){
        if (orderDetailsOptional.isPresent()) {
            OrderDetails orderDetails = orderDetailsOptional.get();
            this.id = orderDetails.getId();
            this.amount = orderDetails.getAmount();
            this.order = orderDetails.getOrder().getId();
            this.product = orderDetails.getProduct().getId();
        }
    }

    public OrderDetailsDTO(Long id, int amount, Long orderId, Long productId){
        this.id = id;
        this.amount = amount;
        this.order = orderId;
        this.product = productId;
    }
}
