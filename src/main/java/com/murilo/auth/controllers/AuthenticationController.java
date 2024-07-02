package com.murilo.auth.controllers;

import com.murilo.auth.dtos.user.AuthenticationDTO;
import com.murilo.auth.dtos.user.LoginResponseDTO;
import com.murilo.auth.dtos.user.RegisterDTO;
import com.murilo.auth.entities.User;
import com.murilo.auth.repositories.UserRepository;
import com.murilo.auth.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        // Validação se já tem um usuário cadastrado no banco de dados
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        // As senhas dos usuários no banco de dados estarão criptografadas em forma de HASH. Posteriormente,
        // é feito uma validação se a senha que o usuário colocou é a mesma que foi criptografada
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}