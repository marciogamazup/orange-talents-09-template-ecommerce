package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.CategoriaRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.ProdutoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.dto.ProdutoDto;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoDto dto){
//        Produto produto = dto.toModel();
//        produtoRepository.save(produto);
//        return  ResponseEntity.ok(produto);
//    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProdutoDto dto) {
        Produto produto = dto.converteParaObjeto(categoriaRepository, usuarioRepository);
        produtoRepository.save(produto);
    }
}
