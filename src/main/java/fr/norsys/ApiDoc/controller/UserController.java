package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.dto.AuthenticationRequest;
import fr.norsys.ApiDoc.dto.AuthenticationResponse;
import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.service.AuthenticationService;
import fr.norsys.ApiDoc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    @PostMapping
    ResponseEntity<AuthenticationResponse> save(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable long id){

        return ResponseEntity.ok(userService.findUserById(id));
    }
    @GetMapping
    ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @GetMapping("/signin/{username}")
    ResponseEntity<User> findByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findUserByUdsername(username));
    }
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(authenticationService.getCurrentUser());
    }

}
