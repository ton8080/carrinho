package com.ecommerce.carrinho.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Random;

@Entity
@Table(name = "product_cart")
@Data
public class ProdutoCarrinho implements Serializable {

	@EmbeddedId
	private ProdutoCarrinhoId produtoCarrinhoId;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cart_id", insertable = false, updatable = false)
	private Cart cart;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	private Integer quantidade;

	private Double valor;

	@Override
	public String toString() {
		return "ProdutoCarrinho{" + "produtoCarrinhoId=" + produtoCarrinhoId + ", quantidade=" + quantidade + ", valor="
				+ valor + '}';
	}

	@Embeddable
	@Data
	public static class ProdutoCarrinhoId implements Serializable {

		@Column(name = "id")
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;

		@Column(name = "cart_id")
		private Integer cartId;

		@Column(name = "product_id")
		private Integer productId;
	}

	@PrePersist
	protected void onCreate() {
		if (produtoCarrinhoId == null) {
			produtoCarrinhoId = new ProdutoCarrinhoId();
			Random random = new Random();
			produtoCarrinhoId.setId(random.nextInt());
			

			// Assuming you have a reference to the Cart entity in the ProdutoCarrinho class
			produtoCarrinhoId.setCartId(cart.getId());

			// Assuming you have a reference to the Product entity in the ProdutoCarrinho
			// class
			produtoCarrinhoId.setProductId(product.getId());
		}
	}
}
