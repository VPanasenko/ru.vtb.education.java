package lesson11.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderId")
    private long id;

    @CreationTimestamp
    @Column(name="orderDate")
    private Date date;

    public Date getDate() {
        return date;
    }

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<OrderComponent> components;

    public Order(User u){
        user = u;
    }
}
