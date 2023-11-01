package com.ecommerce.carrinho.repository;

import com.ecommerce.carrinho.model.entity.Carrinho;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, String> {


    @Transactional
    @Modifying
    @Query("DELETE FROM ProdutoCarrinho pc WHERE pc.produtoCarrinhoId.produtoId = :produtoId")
    void removerProdutoPeloIdProduto(@Param("produtoId") String produtoId);
}
