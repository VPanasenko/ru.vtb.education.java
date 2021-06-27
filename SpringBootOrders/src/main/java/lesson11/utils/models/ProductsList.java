package lesson11.utils.models;

import lesson11.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductsList {
    private List<Product> products;

    public ProductsList(List<Product> products){
        products = products;
    }
}
