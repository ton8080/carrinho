package com.ecommerce.carrinho.service.impl;

import com.ecommerce.carrinho.model.entity.ProdutoCarrinho;
import com.ecommerce.carrinho.model.dto.ProdutoDTO;
import com.ecommerce.carrinho.model.dto.RequestInserirDTO;
import com.ecommerce.carrinho.model.entity.Cart;
import com.ecommerce.carrinho.repository.CarrinhoRepository;
import com.ecommerce.carrinho.repository.ProdutoCarrinhoRepository;
import com.ecommerce.carrinho.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoServiceImpl {

	private final CarrinhoRepository carrinhoRepository;
	private final ProdutoCarrinhoRepository produtoCarrinhoRepository;
	private final ProdutoRepository produtoRepository;

	@Autowired
	public CarrinhoServiceImpl(CarrinhoRepository carrinhoRepository,
			ProdutoCarrinhoRepository produtoCarrinhoRepository, ProdutoRepository produtoRepository) {
		this.carrinhoRepository = carrinhoRepository;
		this.produtoCarrinhoRepository = produtoCarrinhoRepository;
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	public Cart adicionarProdutoExistente(Integer idCarrinho, RequestInserirDTO produtoDTO) {
		var carrinho = carrinhoRepository.findById(idCarrinho)
				.orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

		for (ProdutoCarrinho produtoCarrinho : carrinho.getProdutos()) {
			if (produtoCarrinho.getProduct().getId().equals(produtoDTO.getId())) {
				produtoCarrinho.setQuantidade(produtoCarrinho.getQuantidade() + produtoDTO.getQuantidade());
				produtoCarrinho.setValor(produtoCarrinho.getProduct().getPreco() * produtoCarrinho.getQuantidade());
				System.out.println("Before saving: " + produtoCarrinho);
				if (produtoCarrinho != null) {
					produtoCarrinhoRepository.save(produtoCarrinho);
				}
				System.out.println("After saving: " + produtoCarrinho);
				return carrinhoRepository.save(carrinho);
			}
		}

		// Se o produto não existir no carrinho, você precisa criar um novo
		// ProdutoCarrinho
		ProdutoCarrinho novoProdutoCarrinho = new ProdutoCarrinho();
		novoProdutoCarrinho.setCart(carrinho); // Set the Cart reference
		novoProdutoCarrinho.setProduct(produtoRepository.findById(produtoDTO.getId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado")));
		novoProdutoCarrinho.setQuantidade(produtoDTO.getQuantidade());
		novoProdutoCarrinho.setValor(novoProdutoCarrinho.getProduct().getPreco() * produtoDTO.getQuantidade());
		produtoCarrinhoRepository.save(novoProdutoCarrinho); // Salve após configurar os IDs
		carrinho.getProdutos().add(novoProdutoCarrinho);

		System.out.println("Before saving: " + novoProdutoCarrinho);
		produtoCarrinhoRepository.save(novoProdutoCarrinho);
		System.out.println("After saving: " + novoProdutoCarrinho);
		return carrinhoRepository.save(carrinho);
	}

	public Cart listarProdutos(Integer idCarinho) {
		try {
			System.out.println("ID do Carrinho: " + idCarinho); // Adicione esta linha
			return carrinhoRepository.findCarrinhoById(idCarinho);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public void removerProduto(Integer idProduto, Integer idCarinho, Integer quantidade) {
		var carrinho = carrinhoRepository.findById(idCarinho)
				.orElseThrow(() -> new RuntimeException("Carrinho com ID " + idCarinho + " não encontrado"));

		// Usando Iterator para evitar ConcurrentModificationException
		Iterator<ProdutoCarrinho> iterator = carrinho.getProdutos().iterator();
		while (iterator.hasNext()) {
			ProdutoCarrinho produtoCarrinho = iterator.next();
			if (produtoCarrinho.getProduct().getId().equals(idProduto)) {
				int quantidadeAtual = produtoCarrinho.getQuantidade();
				if (quantidadeAtual > quantidade) {
					produtoCarrinho.setQuantidade(quantidadeAtual - quantidade); // Atualiza a quantidade no carrinho
				} else {
					iterator.remove(); // Remove da lista do carrinho se a quantidade for igual ou menor
				}

				carrinhoRepository.removerProdutoPeloIdProduto(idProduto, quantidade); // Remove do repositório
				break; // Saímos do loop, pois já encontramos e removemos o produto
			}
		}
	}
}
