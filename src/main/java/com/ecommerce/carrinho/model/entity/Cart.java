package com.ecommerce.carrinho.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart implements Serializable {
	
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", produtos=" + produtos +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProdutoCarrinho> produtos;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
