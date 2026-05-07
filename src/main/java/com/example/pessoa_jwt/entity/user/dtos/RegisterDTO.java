package com.example.pessoa_jwt.entity.user.dtos;


import com.example.pessoa_jwt.entity.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {
}
