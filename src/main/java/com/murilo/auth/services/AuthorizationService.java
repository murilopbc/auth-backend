package com.murilo.auth.services;

import com.murilo.auth.dtos.exception.UnauthorizedException;
import com.murilo.auth.dtos.user.RegisterDTO;
import com.murilo.auth.entities.User;
import com.murilo.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public User createUser(RegisterDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        throw new UnauthorizedException();
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }
}
