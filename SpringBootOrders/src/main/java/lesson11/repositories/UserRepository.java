package lesson11.repositories;

import lesson11.model.Order;
import lesson11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u where u in (Select o.user from Order o where o in (Select oc.order from OrderComponent oc where oc.product.id = ?1))")
    Optional<List<User>> getUsersByProduct(long productId);
}
