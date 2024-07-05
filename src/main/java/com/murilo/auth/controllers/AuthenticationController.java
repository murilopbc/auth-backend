package com.murilo.auth.controllers;

import com.murilo.auth.dtos.user.AuthenticationDTO;
import com.murilo.auth.dtos.user.LoginResponseDTO;
import com.murilo.auth.dtos.user.RegisterDTO;
import com.murilo.auth.entities.User;
import com.murilo.auth.repositories.UserRepository;
import com.murilo.auth.services.AuthorizationService;
import com.murilo.auth.services.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthorizationService authorizationService;


    @PostMapping("/login")
    @Transactional
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data, UriComponentsBuilder uriBuilder){

        // As senhas dos usuários no banco de dados estarão criptografadas em forma de HASH. Posteriormente,
        // é feito uma validação se a senha que o usuário colocou é a mesma que foi criptografada
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);
        var uri = uriBuilder.path("").buildAndExpand(newUser.getId().toString());

        return ResponseEntity.created(uri.toUri()).body(newUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.authorizationService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}