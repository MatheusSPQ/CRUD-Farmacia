package com.generation.crudfarmacia.controller;


import com.generation.crudfarmacia.model.Produto;
import com.generation.crudfarmacia.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Object> registerProduto(@Valid @RequestBody Produto produto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.addProduto(produto));
        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listProdutos() {
        return ResponseEntity.ok(produtoService.listAllProdutos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getProdutoById (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.getProdutoById(id));
        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> getAllProdutosBySimilarityWithNome (@PathVariable String nome) {
        return ResponseEntity.ok(produtoService.listProdutoByNome(nome));
    }

    @GetMapping("/categoria/{categoriaNome}")
    public ResponseEntity<Object> getAllProdutosByCategoriaNome (@PathVariable String categoriaNome) {
        return ResponseEntity.ok(produtoService.listProdutosByCategoriaNome(categoriaNome));
    }

    @GetMapping("/receita")
    public ResponseEntity<Object> getAllProdutosByReceita () {
        return ResponseEntity.ok(produtoService.listProdutosByReceitaTrue());
    }

    @PutMapping
    public ResponseEntity<Object> updateProduto(@Valid @RequestBody Produto produto) {
        try {
            return ResponseEntity.ok(produtoService.updateProduto(produto.getId(), produto));
        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteProdutoById(@PathVariable Long id) {
        try {
            produtoService.deleteProduto(id);
        }catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
        }
    }
}
