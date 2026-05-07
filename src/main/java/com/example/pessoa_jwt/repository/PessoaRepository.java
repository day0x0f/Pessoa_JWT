package com.example.pessoa_jwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pessoa_jwt.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}