package lesson11.services;

import lesson11.model.DTO.ProductDTO;
import lesson11.repositories.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAllDTO();
    }
}
