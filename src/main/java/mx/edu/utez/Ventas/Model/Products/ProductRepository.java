package mx.edu.utez.Ventas.Model.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean findById(long id);
    boolean existsByName(String name);
    List<Product> findByName(String name);

    @Modifying
    @Query(
            value = "UPDATE productos set status =: status where  i =:id",
            nativeQuery = true
    )
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id);

}
