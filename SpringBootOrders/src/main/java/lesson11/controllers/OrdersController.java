package lesson11.controllers;

import lesson11.model.*;
import lesson11.services.OrderService;
import lesson11.services.ProductsService;
import lesson11.services.UserService;
import lesson11.utils.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrdersController {
    private final ProductsService productsService;
    private final OrderService orderService;
    private final UserService userService;

    // CREATE
    @PostMapping("/createOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // READ
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productsService.getProducts();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping("/ordersByUserId/{userId}")
//    public List<Order> getOrdersByUserId(@PathVariable long userId) {
//        return orderService.getOrdersByUser(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("No orders made by user id '%1$s'.", userId)));
//    }

    @GetMapping("/usersByProductId/{productId}")
    public List<User> getUsersByProductId(@PathVariable long productId) {
        return userService.getUsersByProduct(productId).orElseThrow(() -> new ResourceNotFoundException(String.format("No users found by product id '%1$s'.", productId)));
    }
}
