package lesson11.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private long id;

    @Column(name="userName")
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    public User(String uName){
        name = uName;
    }
}
