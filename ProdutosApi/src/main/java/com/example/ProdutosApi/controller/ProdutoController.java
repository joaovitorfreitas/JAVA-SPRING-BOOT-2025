package com.example.ProdutosApi.controller;
import com.example.ProdutosApi.model.Produto;
import com.example.ProdutosApi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        String id = UUID.randomUUID().toString();
        produto.setId(id);
        this.produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("{id}")
    public Produto obterPorId(@PathVariable("id") String id){
        return this.produtoRepository.findById(id).orElse(null);
    }


    @GetMapping
    public List<Produto> obterListaProdutos(){
        return this.produtoRepository.findAll();
    }

    @GetMapping("buscarParametro")
    public List<Produto> buscarProdutoPorParametro(@RequestParam("nome") String nome){
        return this.produtoRepository.findByNome(nome);
    }


    @DeleteMapping("{id}")
    public void deletaProduto(@PathVariable("id") String id){
        this.produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizarProduto(@PathVariable("id") String id,
                                 @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
    }
}