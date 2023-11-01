package com.ecommerce.carrinho.controller;

import com.ecommerce.carrinho.model.dto.CarrinhoDTO;
import com.ecommerce.carrinho.service.impl.CarrinhoServiceImpl;
import com.ecommerce.carrinho.model.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinhos/{idCarinho}")
public class CarrinhoController {


    @Autowired
    private CarrinhoServiceImpl carrinhoService;




    @PostMapping("/produto")
    public ResponseEntity<CarrinhoDTO> inserirProduto(@RequestBody ProdutoDTO produto, @PathVariable(name = "idCarinho") String idCarinho) {
        var carrinho = carrinhoService.inserirProduto(idCarinho, produto);
        return ResponseEntity.ok(new CarrinhoDTO(carrinho));
    }

    @GetMapping
    public ResponseEntity<CarrinhoDTO> listarProdutos(@PathVariable(name = "idCarinho") String idCarinho) throws InterruptedException {
        var carrinho = carrinhoService.listarProdutos(idCarinho);
        return ResponseEntity.ok(new CarrinhoDTO(carrinho));
    }


    @DeleteMapping("/produto/{idProduto}")
    public ResponseEntity removerProduto(@PathVariable(name = "idProduto") String idProduto, @PathVariable(name = "idCarinho") String idCarinho) {
        carrinhoService.removerProduto(idCarinho, idProduto);
        return ResponseEntity.noContent().build();
    }
}
