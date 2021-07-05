package lesson11.model.DTO;

import lesson11.model.Order;
import lesson11.model.User;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private Set<Long> orders;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.orders = user.getOrders().stream().map(x -> x.getId()).collect(Collectors.toSet());
    }

    public UserDTO(Optional<User> userOptional){
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            this.id = user.getId();
            this.name = user.getName();
            this.orders = user.getOrders().stream().map(x -> x.getId()).collect(Collectors.toSet());
        }
    }

    public UserDTO(Long id, String name, Set<Order> orders)
    {
        this.id = id;
        this.name = name;
        this.orders = orders.stream().map(x -> x.getId()).collect(Collectors.toSet());
    }
}
