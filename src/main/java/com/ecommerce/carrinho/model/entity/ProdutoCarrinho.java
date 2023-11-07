package com.ecommerce.carrinho.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "product_cart")
@Data
public class ProdutoCarrinho implements Serializable {

    @EmbeddedId
    private ProdutoCarrinhoId produtoCarrinhoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    private Integer quantidade;

    private Double valor;

    @PrePersist
    private void montaPk(){
        this.produtoCarrinhoId = new ProdutoCarrinhoId();
        this.produtoCarrinhoId.setCart_id(this.cart.getId());
        this.produtoCarrinhoId.setProduct_id(this.product.getId());

    }

    @Embeddable
    @Data
    public static class ProdutoCarrinhoId implements Serializable {

        @Column(name = "product_id")
        private Integer product_id;

        @Column(name = "carrinho_id")
        private Integer cart_id;
    }

}

