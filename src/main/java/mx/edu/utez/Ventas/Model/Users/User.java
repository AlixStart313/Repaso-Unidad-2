package mx.edu.utez.Ventas.Model.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Ventas.Model.Products.Product;
import mx.edu.utez.Ventas.Model.transactions.Transactions;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column(unique = true,nullable = false,length = 150)
    private String email;
    @Column(nullable = false,length = 70)
    private String name;
    @Column(nullable = false,length = 150)
    private String surname;
    @Column(nullable = false,length = 150)
    private String lastname;
    @Column(nullable = false,length = 40)
    private String password;
    @Column(nullable = false,columnDefinition = "text")
    private String wishList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Transactions> transaction;

}
