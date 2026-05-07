package com.example.pessoa_jwt.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.pessoa_jwt.entity.User;
import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){

        try{

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    public String validateToken(String token){

        try{

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant generateExpirationDate(){

        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}