//package lesson11.repositories;
//
//import lesson11.model.Order;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface OrderRepository extends JpaRepository<Order, Long> {
////    Optional<List<Order>> getAllByUserId(long userId);
////
////    @Query("Select o from Order o where o.id in (Select oc from OrderComponent oc where oc.product.id = ?1)")
////    Optional<List<Order>> getOrdersByProduct(long productId);
//}
