package com.ecommerce.carrinho.model.dto;

import com.ecommerce.carrinho.model.entity.ProdutoCarrinho;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCarrinhoDTO {

    private ProdutoDTO produto;
    private Integer quantidade;
    private Double valor;

    public ProdutoCarrinhoDTO(ProdutoCarrinho produtoCarrinho) {
        produto = new ProdutoDTO(produtoCarrinho.getProduct());
        quantidade = produto.getQuantidade();
        valor = produtoCarrinho.getValor();
    }
}
