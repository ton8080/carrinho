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
public class RequestInserirDTO {
    private Integer id;
    private Integer quantidade;

    public RequestInserirDTO(Product produto, Integer quantidade) {
        this.id = produto.getId();
        this.quantidade = quantidade;
    }

}
