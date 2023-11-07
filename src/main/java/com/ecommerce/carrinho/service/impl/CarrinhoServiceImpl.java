package com.ecommerce.carrinho.service.impl;

import com.ecommerce.carrinho.model.entity.ProdutoCarrinho;
import com.ecommerce.carrinho.model.dto.ProdutoDTO;
import com.ecommerce.carrinho.model.entity.Cart;
import com.ecommerce.carrinho.repository.CarrinhoRepository;
import com.ecommerce.carrinho.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoServiceImpl {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public Cart adicionarProdutoExistente(Integer idCarinho, ProdutoDTO produtoDTO) {
	    var carrinho = carrinhoRepository.findById(idCarinho).orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
	    
	    for (ProdutoCarrinho produtoCarrinho : carrinho.getProdutos()) {
	        if (produtoCarrinho.getProduct().getId().equals(produtoDTO.getId())) {
	            produtoCarrinho.setQuantidade(produtoCarrinho.getQuantidade() + produtoDTO.getQuantidade());
	            produtoCarrinho.setValor(produtoCarrinho.getProduct().getPreco() * produtoCarrinho.getQuantidade());
	            return carrinhoRepository.save(carrinho);
	        }
	    }
	    
	    // Se o produto não existir no carrinho, você precisa criar um novo ProdutoCarrinho
	    ProdutoCarrinho novoProdutoCarrinho = new ProdutoCarrinho();
	    novoProdutoCarrinho.setProduct(produtoRepository.findById(produtoDTO.getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado")));
	    novoProdutoCarrinho.setQuantidade(produtoDTO.getQuantidade());
	    novoProdutoCarrinho.setValor(novoProdutoCarrinho.getProduct().getPreco() * produtoDTO.getQuantidade());
	    novoProdutoCarrinho.setCart(carrinho);
	    carrinho.getProdutos().add(novoProdutoCarrinho);
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

	public void removerProduto(Integer idProduto, Integer idCarinho) {
	    var carrinho = carrinhoRepository.findById(idCarinho)
	            .orElseThrow(() -> new RuntimeException("Carrinho com ID " + idCarinho + " não encontrado"));

	    // Usando Iterator para evitar ConcurrentModificationException
	    Iterator<ProdutoCarrinho> iterator = carrinho.getProdutos().iterator();
	    while (iterator.hasNext()) {
	        ProdutoCarrinho produtoCarrinho = iterator.next();
	        if (produtoCarrinho.getProduct().getId().equals(idProduto)) {
	            iterator.remove(); // Remove da lista do carrinho
	            carrinhoRepository.removerProdutoPeloIdProduto(idProduto); // Remove do repositório
	            break; // Saímos do loop, pois já encontramos e removemos o produto
	        }
	    }
	}
}
