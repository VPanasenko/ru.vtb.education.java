package lesson11.services;

import lesson11.model.DTO.ProductDTO;
import lesson11.model.DTO.UserDTO;
import lesson11.model.OrderDetails;
import lesson11.model.Product;
import lesson11.repositories.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllDTO();
    }

    public Optional<ProductDTO> getProductById(long id) {
        return productRepository.findDTOById(id);
    }

    public ProductDTO updateProduct(Product product) {
        Optional<ProductDTO> foundProduct = productRepository.findDTOById(product.getId());
        if (foundProduct.isPresent()) {
            Product p = new Product();
            p.setId(product.getId());
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setOrderDetails(
                    foundProduct.get().getOrderDetails().stream().map(x ->
                            {
                                OrderDetails od = new OrderDetails();
                                od.setId(x);
                                return od;
                            }
                    ).collect(Collectors.toSet()));
            Product pResult = productRepository.save(p);
            return new ProductDTO(pResult);
        }
        return null;
    }
}
