package com.ecommerce.carrinho.service.impl;

import com.ecommerce.carrinho.model.entity.ProdutoCarrinho;
import com.ecommerce.carrinho.model.dto.ProdutoDTO;
import com.ecommerce.carrinho.model.entity.Carrinho;
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

    public Carrinho inserirProduto(String idCarinho, ProdutoDTO produtoDTO) {
        var carrinho = carrinhoRepository.findById(idCarinho).get();
        var produto = produtoRepository.findById(produtoDTO.getId()).get();
        ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho();
        produtoCarrinho.setProduto(produto);
        produtoCarrinho.setQuantidade(produtoDTO.getQuantidade());
        produtoCarrinho.setValor(produto.getValor());
        produtoCarrinho.setCarrinho(carrinho);
        carrinho.getProdutos().add(produtoCarrinho);
        return carrinhoRepository.save(carrinho);
    }
    public Carrinho listarProdutos(String idCarinho) {
        return carrinhoRepository.findById(idCarinho).orElseThrow(() -> new RuntimeException("Carrinho com ID " + idCarinho + " não encontrado"));
    }

    public void removerProduto(String idCarinho, String idProduto) {

        var carrinho = carrinhoRepository.findById(idCarinho).orElseThrow(() -> new RuntimeException("Carrinho com ID " + idCarinho + " não encontrado"));

        for (ProdutoCarrinho produtoCarrinho : carrinho.getProdutos()){
          if (produtoCarrinho.getProduto().getId().equals(idProduto)){
	         carrinhoRepository.removerProdutoPeloIdProduto(idProduto);
          }
        }

    }
}
