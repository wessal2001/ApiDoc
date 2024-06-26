package fr.norsys.ApiDoc.service;

import com.sun.security.auth.UserPrincipal;
import fr.norsys.ApiDoc.exception.NotFoundException;
import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.impl.UserDaoImpl;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDaoImpl userRepository;
    public User saveUser(User request){
        Long idUser= userRepository.save(request);
         return User.builder()
                 .idUser(idUser)
                 .username(request.getUsername())
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
    public User findUserByUdsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new
                    NotFoundException("User not found");
        });
    }


}
