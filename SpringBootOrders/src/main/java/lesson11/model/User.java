package lesson11.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

//@NamedEntityGraph(
//        name = "User.with-orders",
//        attributeNodes = {
//                @NamedAttributeNode("orders")
//        }
//)
//@NamedEntityGraph(
//        name = "User.with-orders-and-components",
//        attributeNodes = {
//                @NamedAttributeNode(value = "orders", subgraph = "order-components")
//        },
//        subgraphs = {
//                @NamedSubgraph(
//                        name = "order-components",
//                        attributeNodes = {
//                                @NamedAttributeNode("components")
//                        }
//                )
//        }
//)

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="user_name")
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public User(String uName){
        name = uName;
    }
}
