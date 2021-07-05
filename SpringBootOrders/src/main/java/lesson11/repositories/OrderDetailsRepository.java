package lesson11.repositories;

import lesson11.model.DTO.OrderDetailsDTO;
import lesson11.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    @Query("select new lesson11.model.DTO.OrderDetailsDTO(od) from OrderDetails od")
    List<OrderDetailsDTO> findAllDTO();
}
