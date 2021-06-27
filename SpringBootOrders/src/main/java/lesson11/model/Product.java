package lesson11.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long id;

    @Column(name = "productTitle")
    private String title;

    @Column(name="price")
    private int price;

    @OneToMany(mappedBy = "product")
    private Set<OrderComponent> components;

    public Product(String pTitle, int pPrice) {
        this.title = pTitle;
        this.price = pPrice;
    }
}
