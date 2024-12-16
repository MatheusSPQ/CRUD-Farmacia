package com.generation.crudfarmacia.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatorio")
    @Size(min = 2, max = 252)
    private String nome;

    @NotBlank(message = "O atributo descricao é obrigatorio")
    @Size(min = 10, max = 1000)
    private String descricao;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;

    @NotNull(message = "O atributo precisaReceita é obrigatorio")
    private Boolean receita;

    @NotNull(message = "O atributo preco é obrigatorio!")
    private Long preco;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public @NotNull(message = "O atributo precisaReceita é obrigatorio") Boolean getReceita() {
        return receita;
    }

    public void setReceita(@NotNull(message = "O atributo precisaReceita é obrigatorio") Boolean receita) {
        this.receita = receita;
    }

    public @NotNull(message = "O atributo preco é obrigatorio!") Long getPreco() {
        return preco;
    }

    public void setPreco(@NotNull(message = "O atributo preco é obrigatorio!") Long preco) {
        this.preco = preco;
    }
}
