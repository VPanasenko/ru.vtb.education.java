package lesson11.test.services;

import lesson11.model.DTO.ProductDTO;
import lesson11.model.Product;
import lesson11.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import lesson11.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    // Одновременно не получается в рамках одного класса использовать и живой объект, и моковый объект одного типа.
    @Autowired
    private ProductRepository productRepository;

//    @MockBean
//    private ProductRepository mockProductRepository;

    @Test
    public void UpdateProductTest(){

        Optional<Product> optionalProduct = productRepository.findById(1L);

        Product updatedProduct = optionalProduct.get();

        updatedProduct.setName("Test updatedProduct");

        int newPrice = 44444;
        updatedProduct.setPrice(newPrice);

        ProductDTO productDTO = productService.updateProduct(updatedProduct);

        Assertions.assertEquals(productDTO.getPrice(), newPrice);
    }
}
