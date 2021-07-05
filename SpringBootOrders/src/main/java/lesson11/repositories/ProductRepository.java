package lesson11.repositories;

import lesson11.model.DTO.ProductDTO;
import lesson11.model.DTO.UserDTO;
import lesson11.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("Select new lesson11.model.DTO.ProductDTO(p) from Product p")
    List<ProductDTO> findAllDTO();

    @Query("Select new lesson11.model.DTO.ProductDTO(p) from Product p where p.id = :id")
    Optional<ProductDTO> findDTOById(Long id);

}
