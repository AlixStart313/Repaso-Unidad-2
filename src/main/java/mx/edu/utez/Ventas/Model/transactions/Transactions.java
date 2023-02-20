package mx.edu.utez.Ventas.Model.transactions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Ventas.Model.Category.Category;
import mx.edu.utez.Ventas.Model.Products.Product;
import mx.edu.utez.Ventas.Model.Users.User;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long folio;
    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
}
