package com.ecommerce.carrinho.repository;

import com.ecommerce.carrinho.model.entity.ProdutoCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoCarrinhoRepository extends JpaRepository<ProdutoCarrinho, Integer> {
    // Adicione métodos personalizados, se necessário
}
