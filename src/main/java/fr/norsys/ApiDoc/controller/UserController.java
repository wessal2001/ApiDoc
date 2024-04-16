package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    ResponseEntity<User> save(@RequestBody User request){
        return ResponseEntity.ok(userService.saveUser(request));
    }
    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable long id){

        return ResponseEntity.ok(userService.findUserById(id));
    }
    @GetMapping
    ResponseEntity<List<User>> getAllUsers(){

        return ResponseEntity.ok(userService.findAllUsers());
    }
}
