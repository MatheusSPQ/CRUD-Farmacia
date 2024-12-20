package com.generation.crudfarmacia.repository;

import com.generation.crudfarmacia.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
    public List<Produto> findAllByCategoria_nomeContainingIgnoreCase(@Param("nome") String categoriaNome);
    public List<Produto> findAllByReceitaFalse();

}
