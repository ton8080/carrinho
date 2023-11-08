package com.ecommerce.carrinho.model.dto;

import com.ecommerce.carrinho.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;

    public ProdutoDTO(Product produto, Integer quantidade) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.quantidade = quantidade;
    }

}
