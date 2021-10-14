package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.OpiniaoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.ProdutoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.dto.OpiniaoDto;
import br.com.zupacademy.marcio.ecommerce.entities.Opiniao;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos/opinioes/{id}")
public class OpinioesController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    OpiniaoRepository opiniaoRepository;

    @PostMapping
    @Transactional
    public void adicionaOpinioes(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoDto dto) {

        Usuario usuarioQueOpina = usuarioRepository.findByEmail("marciogama@gmail.com").get();
        Produto produto = produtoRepository.findById(id).get();

        Opiniao opiniao = dto.converteParaObjeto(usuarioQueOpina, produto);
        opiniaoRepository.save(opiniao);
    }
}
