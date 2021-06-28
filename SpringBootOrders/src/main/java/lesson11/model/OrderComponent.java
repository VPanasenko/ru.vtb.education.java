//package lesson11.model;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@Table(name="order_components")
//@NoArgsConstructor
//public class OrderComponent {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private long id;
//
//    @ManyToOne
//    @JoinColumn(name="order_id")
//    private Order order;
//
//    @ManyToOne
//    @JoinColumn(name="product_id")
//    private Product product;
//
//    @Column(name="productFixedPrice")
//    private int productFixedPrice;
//
//    @Column(name="productAmount")
//    private int productAmount;
//
//    public OrderComponent(Order o, Product p, int price, int amount){
//        order = o;
//        product = p;
//        productFixedPrice = price;
//        productAmount = amount;
//    }
//}
