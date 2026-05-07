package com.example.pessoa_jwt.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pessoa_jwt.entity.Pessoa;
import com.example.pessoa_jwt.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> findAll(){
        return repository.findAll();
    }

    public Optional<Pessoa> getPessoaById(Long id){
        return repository.findById(id);
    }

    public Pessoa save(Pessoa pessoa){
        return repository.save(pessoa);
    }

    public Optional<Pessoa> update(Long id, Pessoa pessoaDetails){

        return repository.findById(id)
                .map(pessoa -> {

                    pessoa.setNome(pessoaDetails.getNome());

                    return repository.save(pessoa);
                });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}