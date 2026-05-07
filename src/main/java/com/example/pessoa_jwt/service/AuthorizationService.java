package com.example.pessoa_jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.pessoa_jwt.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return repository.findByLogin(username);
    }
}