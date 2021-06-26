package lesson10.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="items")
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "customerName")
    private String customerName;

    @Column(name="price")
    private int price;

    public Item(String title, String customerName, int price) {
        this.title = title;
        this.customerName = customerName;
        this.price = price;
    }
}
