package mx.edu.utez.Ventas.Model.Products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Ventas.Model.Category.Category;
import mx.edu.utez.Ventas.Model.transactions.Transactions;

import java.util.List;

@Entity
@Table(name = "Productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true,nullable = false,length = 150)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false,columnDefinition = "tinyint default 1")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @OneToMany(mappedBy ="product")
    @JsonIgnore
    private List<Transactions> transactions;


}
