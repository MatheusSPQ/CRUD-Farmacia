package com.generation.crudfarmacia.controller;


import com.generation.crudfarmacia.model.Categoria;
import com.generation.crudfarmacia.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Object> registerCategoria(@Valid @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.addCategoria(categoria));
        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listCategorias() {
        return ResponseEntity.ok(categoriaService.listAllCategorias());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getCategoriaById (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.getCategoriaById(id));
        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> getAllCategoriasBySimilarityWithNome (@PathVariable String nome) {
        return ResponseEntity.ok(categoriaService.listCategoriasByNome(nome));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Object> getAllCategoriasBySimilarityWithDescricao (@PathVariable String descricao) {
        return ResponseEntity.ok(categoriaService.listCategoriasByDescricao(descricao));
    }

    @PutMapping
    public ResponseEntity<Object> updateCategoria(@Valid @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok(categoriaService.updateCategoria(categoria.getId(), categoria));
        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteCategoriaById(@PathVariable Long id) {
        try {
            categoriaService.deleteCategoria(id);
        }catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
        }
    }





}
