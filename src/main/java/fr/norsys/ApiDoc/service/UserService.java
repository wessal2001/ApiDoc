package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.exception.NotFoundException;
import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.impl.UserDaoImpl;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDaoImpl userRepository;
    public User saveUser(User request){
         userRepository.save(request);
         return User.builder().username(request.getUsername())
                 .password(request.getPassword())
                 .email(request.getEmail()).build();
    }
    public User findUserById(long id){
        return userRepository.findById(id).orElseThrow(() -> {
            throw new
                    NotFoundException("User not found");
        });
    }
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

}
