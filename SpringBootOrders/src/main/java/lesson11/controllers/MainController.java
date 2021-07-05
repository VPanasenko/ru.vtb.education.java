package lesson11.controllers;

import lesson11.model.*;
import lesson11.model.DTO.OrderDTO;
import lesson11.model.DTO.OrderDetailsDTO;
import lesson11.model.DTO.ProductDTO;
import lesson11.model.DTO.UserDTO;
import lesson11.services.OrderDetailsService;
import lesson11.services.OrderService;
import lesson11.services.ProductService;
import lesson11.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderDetailsService orderDetailsService;

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable long id) {
        return userService.getUserById(id).get();
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/orderDetails")
    public List<OrderDetailsDTO> getOrderDetails() {
        return orderDetailsService.getAllOrderDetails();
    }

    @PostMapping("/orders/create")
    public OrderDTO createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }
}
