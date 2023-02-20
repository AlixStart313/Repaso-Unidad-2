package mx.edu.utez.Ventas.Service.category;

import mx.edu.utez.Ventas.Model.Category.Category;
import mx.edu.utez.Ventas.Model.Category.CategoryRepository;
import mx.edu.utez.Ventas.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class CategoryService {
     @Autowired
    private CategoryRepository categoryRepository;

     @Transactional(readOnly = true)
    public CustomResponse<List<Category>> getAll(){
         return  new CustomResponse<>(
                 this.categoryRepository.findAll(),false,200,"ok"
         );
     }

     @Transactional(readOnly = true)
    public CustomResponse<Category> getOne(Long id){
         return new CustomResponse<>(
                 this.categoryRepository.findById(id).get(),false,200,"ok"
         );
     }

     @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Category> insert(Category category){
         if(this.categoryRepository.existsByName(category.getName())){
             return new CustomResponse<>(
                     null,true,400,"Esta categoria ya existe"
             );
         }

         return new CustomResponse<>(
                 this.categoryRepository.saveAndFlush(category),false,200,"ok"
         );
     }
}
