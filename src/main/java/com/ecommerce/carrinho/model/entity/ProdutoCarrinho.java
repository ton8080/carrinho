package com.ecommerce.carrinho.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "produto_carrinho")
@Data
public class ProdutoCarrinho implements Serializable {

    @EmbeddedId
    private ProdutoCarrinhoId produtoCarrinhoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "carrinho_id", insertable = false, updatable = false)
    private Carrinho carrinho;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produto;

    private Integer quantidade;

    private Double valor;

    @PrePersist
    private void montaPk(){
        this.produtoCarrinhoId = new ProdutoCarrinhoId();
        this.produtoCarrinhoId.setCarrinhoId(this.carrinho.getId());
        this.produtoCarrinhoId.setProdutoId(this.produto.getId());

    }

    @Embeddable
    @Data
    public static class ProdutoCarrinhoId implements Serializable {

        @Column(name = "produto_id")
        private String produtoId;

        @Column(name = "carrinho_id")
        private String carrinhoId;
    }

}

