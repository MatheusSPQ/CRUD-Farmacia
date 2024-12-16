package com.generation.crudfarmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatorio")
    @Size(min = 2, max = 252)
    private String nome;

    @NotBlank(message = "O atributo descricao é obrigatorio")
    @Size(min = 10, max = 1000)
    private String descricao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categoria")
    private List<Produto> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public @NotBlank(message = "O atributo nome é obrigatorio") @Size(min = 2, max = 252) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O atributo nome é obrigatorio") @Size(min = 2, max = 252) String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O atributo descricao é obrigatorio") @Size(min = 10, max = 1000) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "O atributo descricao é obrigatorio") @Size(min = 10, max = 1000) String descricao) {
        this.descricao = descricao;
    }
}
