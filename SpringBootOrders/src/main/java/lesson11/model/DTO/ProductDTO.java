package lesson11.model.DTO;

import lesson11.model.OrderDetails;
import lesson11.model.Product;
import lombok.Data;

import javax.persistence.Column;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int price;
    private Set<Long> orderDetails;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.orderDetails = product.getOrderDetails().stream().map(x -> x.getId()).collect(Collectors.toSet());
    }

    public ProductDTO(Optional<Product> productOptional) {
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            this.id = product.getId();
            this.name = product.getName();
            this.price = product.getPrice();
            this.orderDetails = product.getOrderDetails().stream().map(x -> x.getId()).collect(Collectors.toSet());
        }
    }

    public ProductDTO(Long id, String name, int price, Set<OrderDetails> orderDetails){
        this.id = id;
        this.name = name;
        this.price = price;
        this.orderDetails = orderDetails.stream().map(x -> x.getId()).collect(Collectors.toSet());
    }
}
