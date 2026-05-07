package com.example.pessoa_jwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.pessoa_jwt.config.security.TokenService;
import com.example.pessoa_jwt.entity.User;
import com.example.pessoa_jwt.entity.user.dtos.*;
import com.example.pessoa_jwt.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody AuthenticationDTO data
    ){

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        data.login(),
                        data.password()
                );

        var auth = authenticationManager.authenticate(usernamePassword);

        var token =
                tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterDTO data
    ){

        if(repository.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword =
                new BCryptPasswordEncoder()
                        .encode(data.password());

        User newUser =
                new User(
                        data.login(),
                        encryptedPassword,
                        data.role()
                );

        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
