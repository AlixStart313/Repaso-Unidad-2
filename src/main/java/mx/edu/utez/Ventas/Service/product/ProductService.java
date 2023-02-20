package mx.edu.utez.Ventas.Service.product;


import mx.edu.utez.Ventas.Model.Products.Product;
import mx.edu.utez.Ventas.Model.Products.ProductRepository;
import mx.edu.utez.Ventas.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Product>> getAll(){
        return  new CustomResponse<>(
                this.productRepository.findAll(),
                false,200,"OK"
        );
    }

    @Transactional(readOnly = true)
    public  CustomResponse<Product> getOne(Long id){
        return  new CustomResponse<>(
                this.productRepository.findById(id).get(),
                false,200,"ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Product> insert(Product product){
        if(this.productRepository.existsByName(product.getName())){
          return new CustomResponse<>(
                  null,true,400,"Este producto ya existe"
          );
        }
        return new CustomResponse<>(
                this.productRepository.saveAndFlush(product),false,200,"OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changeStatus(Product product){
        if(!this.productRepository.existsById(product.getId())){
            return new CustomResponse<>(
                    0,true,400,"No se pudo modificar el estato de este producto"
            );
        }
        return  new CustomResponse<>(
                this.productRepository.updateStatusById(product.isStatus(), product.getId()),false,200,"ok"
        );
    }

}
