package com.ecommerce.carrinho.repository;

import com.ecommerce.carrinho.model.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Cart, Integer> {
	
	@Query("SELECT c FROM Cart c WHERE c.id = ?1")
	Cart findCarrinhoById(Integer id);


	@Modifying
	@Query("DELETE FROM ProdutoCarrinho pc WHERE pc.produtoCarrinhoId.productId = :produtoId AND pc.produtoCarrinhoId.cartId = :cartId")
	void removerProdutoPeloIdProduto(Integer produtoId, Integer cartId);

}
