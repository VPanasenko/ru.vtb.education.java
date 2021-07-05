package lesson11.repositories;

import lesson11.model.DTO.OrderDTO;
import lesson11.model.DTO.UserDTO;
import lesson11.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("Select new lesson11.model.DTO.OrderDTO(o) from Order o")
    List<OrderDTO> findAllDTO();

    @Query("Select new lesson11.model.DTO.OrderDTO(o) from Order o where o.id = :id")
    Optional<OrderDTO> findDTOById(Long id);
}
