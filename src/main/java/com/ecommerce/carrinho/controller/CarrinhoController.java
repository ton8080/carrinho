package com.ecommerce.carrinho.controller;

import com.ecommerce.carrinho.model.dto.CarrinhoDTO;
import com.ecommerce.carrinho.service.impl.CarrinhoServiceImpl;
import com.ecommerce.carrinho.model.dto.ProdutoDTO;
import com.ecommerce.carrinho.model.dto.RequestInserirDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinhos/{idCarinho}")
public class CarrinhoController {


    @Autowired
    private CarrinhoServiceImpl carrinhoService;




    @PostMapping("/produto")
    public ResponseEntity<CarrinhoDTO> inserirProduto(@RequestBody RequestInserirDTO produto, @PathVariable(name = "idCarinho") Integer idCarinho) {
        var carrinho = carrinhoService.adicionarProdutoExistente(idCarinho, produto);
        return ResponseEntity.ok(new CarrinhoDTO(carrinho));
    }


    @GetMapping
    public ResponseEntity<CarrinhoDTO> listarProdutos(@PathVariable(name = "idCarinho") Integer idCarinho) throws InterruptedException {
        var carrinho = carrinhoService.listarProdutos(idCarinho);
        return ResponseEntity.ok(new CarrinhoDTO(carrinho));
    }


    @DeleteMapping("/produto/{idProduto}")
    public ResponseEntity removerProduto(@PathVariable(name = "idCarinho") Integer idCarinho, @PathVariable(name = "idProduto") Integer idProduto,
    		@RequestParam(name = "qtd", required = false, defaultValue = "1") Integer quantidade) {
        carrinhoService.removerProduto(idProduto, idCarinho, quantidade);
        return ResponseEntity.noContent().build();
    }
}
