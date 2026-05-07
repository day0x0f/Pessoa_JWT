package com.example.pessoa_jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pessoa_jwt.entity.Pessoa;
import com.example.pessoa_jwt.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public List<Pessoa> getAllPessoas(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id){

        Optional<Pessoa> pessoa = service.getPessoaById(id);

        return pessoa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoa createPessoa(@RequestBody Pessoa pessoa){
        return service.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(
            @PathVariable Long id,
            @RequestBody Pessoa pessoaDetails
    ){

        Optional<Pessoa> pessoa = service.update(id, pessoaDetails);

        return pessoa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}