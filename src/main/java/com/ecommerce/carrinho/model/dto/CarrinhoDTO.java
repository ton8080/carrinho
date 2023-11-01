package com.ecommerce.carrinho.model.dto;

import com.ecommerce.carrinho.model.entity.ProdutoCarrinho;
import com.ecommerce.carrinho.model.entity.Carrinho;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDTO {

   private List<ProdutoCarrinhoDTO> produtos = new ArrayList<>();
   private Double valorTotal = Double.valueOf(0);
    public CarrinhoDTO(Carrinho carrinho) {
        for (ProdutoCarrinho produtoCarrinho : carrinho.getProdutos()){
            produtos.add(new ProdutoCarrinhoDTO(produtoCarrinho));
            valorTotal += produtoCarrinho.getValor();
        }
    }
}
