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
    private Integer quantidade;

    public ProdutoDTO(Product produto) {
        this.id = produto.getId();
    }

}
