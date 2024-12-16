package com.generation.crudfarmacia.service;

import com.generation.crudfarmacia.model.Categoria;
import com.generation.crudfarmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    //Post
    public Optional<Categoria> addCategoria(Categoria categoria) throws HttpClientErrorException {
        if(categoriaRepository.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Categoria ja existente");
        }

        return Optional.of(categoriaRepository.save(categoria));

    }


    //Get
    public List<Categoria> listAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoriaById(Long id) throws HttpClientErrorException {
        if(categoriaRepository.existsById(id)) {
            return categoriaRepository.findById(id);
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Categoria com o id " + id +" não encontrada");
    }

    public List<Categoria> listCategoriasByNome(String nome) {
        return categoriaRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public List<Categoria> listCategoriasByDescricao(String descricao) {
        return categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }

    //Put - update
    public Optional<Categoria> updateCategoria(Long id, Categoria categoria) throws HttpClientErrorException {
        if(categoriaRepository.existsById(id)) {
            return Optional.of(categoriaRepository.save(categoria));
        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Categoria com o id " + id +" não encontrada");
    }

    //Delete
    public void deleteCategoria(Long id) throws HttpClientErrorException {
        if(!categoriaRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Categoria com o id " + id +" não encontrada");
        }
        categoriaRepository.deleteById(id);

    }




}
