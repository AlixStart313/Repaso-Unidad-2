package mx.edu.utez.Ventas.Service.user;


import mx.edu.utez.Ventas.Model.Users.User;
import mx.edu.utez.Ventas.Model.Users.UserRepository;
import mx.edu.utez.Ventas.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getAll(){
        return new CustomResponse<>(
                this.userRepository.findAll(),false,200,"ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<User> getOne(Long id){
        return new CustomResponse<>(
                this.userRepository.findById(id).get(),false,200,"ok"
        );
    }

     @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> changeStatus(User user){
        if(!this.userRepository.existsById(user.getId())){
            return new CustomResponse<>(
                    null,true,400,"Esta persona ya esta registrada"
            );
        }
        return new CustomResponse<>(
                this.userRepository.saveAndFlush(user),false,200,"OK"
        );
     }
}
