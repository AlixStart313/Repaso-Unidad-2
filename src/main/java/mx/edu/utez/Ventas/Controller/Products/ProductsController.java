package mx.edu.utez.Ventas.Controller.Products;

import jakarta.validation.Valid;
import mx.edu.utez.Ventas.Controller.Products.ProductosDtos.ProductosDtos;
import mx.edu.utez.Ventas.Model.Products.Product;
import mx.edu.utez.Ventas.Model.Products.ProductRepository;
import mx.edu.utez.Ventas.Service.product.ProductService;
import mx.edu.utez.Ventas.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-ventas/productos")
@CrossOrigin(origins = {"*"})
public class ProductsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Product>>> getAll(){
        return new ResponseEntity<>(
                this.productService.getAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CustomResponse<Product>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.productService.getOne(id),HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Product>> saveProduct(@RequestBody ProductosDtos productosDtos, @Valid BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.productService.insert((p))
        )
    }
}
