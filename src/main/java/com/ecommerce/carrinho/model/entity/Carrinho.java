package com.ecommerce.carrinho.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carrinho")
@Data
public class Carrinho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProdutoCarrinho> produtos;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
