package com.ecommerce.carrinho.service.impl;

import com.ecommerce.carrinho.model.entity.ProdutoCarrinho;
import com.ecommerce.carrinho.model.dto.ProdutoDTO;
import com.ecommerce.carrinho.model.entity.Cart;
import com.ecommerce.carrinho.repository.CarrinhoRepository;
import com.ecommerce.carrinho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoServiceImpl {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Cart inserirProduto(Integer idCarinho, ProdutoDTO produtoDTO) {
        var carrinho = carrinhoRepository.findById(idCarinho).get();
        var produto = produtoRepository.findById(produtoDTO.getId()).get();
        ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho();
        produtoCarrinho.setProduct(produto);
        produtoCarrinho.setQuantidade(produtoDTO.getQuantidade());
        produtoCarrinho.setValor(produto.getPreco());
        produtoCarrinho.setCart(carrinho);
        carrinho.getProdutos().add(produtoCarrinho);
        return carrinhoRepository.save(carrinho);
    }
    public Cart listarProdutos(Integer idCarinho) {
        return carrinhoRepository.findById(idCarinho).orElseThrow(() -> new RuntimeException("Carrinho com ID " + idCarinho + " não encontrado"));
    }

    public void removerProduto(Integer idProduto, Integer idCarinho) {

        var carrinho = carrinhoRepository.findById(idCarinho).orElseThrow(() -> new RuntimeException("Carrinho com ID " + idCarinho + " não encontrado"));

        for (ProdutoCarrinho produtoCarrinho : carrinho.getProdutos()){
          if (produtoCarrinho.getProduct().getId().equals(idProduto)){
	         carrinhoRepository.removerProdutoPeloIdProduto(idProduto);
          }
        }

    }
}
