package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.dto.AuthenticationRequest;
import fr.norsys.ApiDoc.dto.AuthenticationResponse;
import fr.norsys.ApiDoc.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    public AuthenticationResponse register(User request){
        var user= User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User saved=userService.saveUser(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(saved.getUsername());

        var jwt=jwtService.generateToken(userDetails);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user=userDetailsService.loadUserByUsername(request.getUsername());
        var jwt=jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }
}

