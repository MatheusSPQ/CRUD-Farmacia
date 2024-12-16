package com.generation.crudfarmacia.service;


import com.generation.crudfarmacia.model.Produto;
import com.generation.crudfarmacia.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    //Post
    public Optional<Produto> addProduto(Produto produto) throws HttpClientErrorException {
        return Optional.of(produtoRepository.save(produto));
    }

    //Get
    public List<Produto> listAllProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) throws HttpClientErrorException {
        if(produtoRepository.existsById(id)) {
            return produtoRepository.findById(id);
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Produto com o id " + id +" não encontrado");
    }

    public List<Produto> listProdutoByNome(String nome) {
        return produtoRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> listProdutosByCategoriaNome(String CategoriaNome) {
        return produtoRepository.findAllByCategoria_nomeContainingIgnoreCase(CategoriaNome);
    }

    public List<Produto> listProdutosByReceitaFalse(){
        return produtoRepository.findAllByReceitaFalse();
    }

    //Put - update
    public Optional<Produto> updateProduto(Long id, Produto produto) throws HttpClientErrorException {
        if(produtoRepository.existsById(id)) {
            return Optional.of(produtoRepository.save(produto));
        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Produto com o id " + id +" não encontrado");
    }

    //Delete
    public void deleteProduto(Long id) throws HttpClientErrorException {
        if(!produtoRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Produto com o id " + id +" não encontrado");
        }
        produtoRepository.deleteById(id);

    }
}
