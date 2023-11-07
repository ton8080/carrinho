package com.ecommerce.carrinho.repository;


import com.ecommerce.carrinho.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Product, Integer> {
}
