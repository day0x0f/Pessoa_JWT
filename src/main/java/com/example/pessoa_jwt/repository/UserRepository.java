package com.example.pessoa_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pessoa_jwt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);
}