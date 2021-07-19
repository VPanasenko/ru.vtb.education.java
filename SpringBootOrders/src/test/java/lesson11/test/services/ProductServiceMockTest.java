package lesson11.test.services;

import lesson11.model.DTO.ProductDTO;
import lesson11.model.Product;
import lesson11.repositories.ProductRepository;
import lesson11.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;

@SpringBootTest(classes = { ProductRepository.class, ProductService.class })
@ActiveProfiles("test")
public class ProductServiceMockTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository mockProductRepository;


    @Test
    public void UpdateMockProductTest(){
        Product mockedProduct = new Product();
        mockedProduct.setId(1032L);
        mockedProduct.setName("Mocked Product");
        mockedProduct.setPrice(1032);
        mockedProduct.setOrderDetails(new HashSet<>());

        Mockito
                .doReturn(Optional.of(mockedProduct))
                .when(mockProductRepository)
                .findById(1032L);

        Optional<Product> optionalProduct = mockProductRepository.findById(1032L);

        Product updatedProduct = optionalProduct.get();

        ProductDTO mockedProductDTO = new ProductDTO(updatedProduct);

        Mockito
                .doReturn(Optional.of(mockedProductDTO))
                .when(mockProductRepository)
                .findDTOById(1032L);

        updatedProduct.setName("Test updatedMockedProduct");

        int newPrice = 44444;
        updatedProduct.setPrice(newPrice);

        // Это неправильно, на мой взгляд, но иначе при обновлении объекта в сервисе ProductService в строке
        // Product pResult = productRepository.save(p);
        // возвращался null
        Mockito
                .doReturn(mockedProduct)
                .when(mockProductRepository)
                .save(mockedProduct);

        ProductDTO productDTO = productService.updateProduct(updatedProduct);

        Assertions.assertEquals(productDTO.getPrice(), newPrice);
    }
}
