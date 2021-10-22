package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.OpiniaoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.ProdutoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.dto.OpiniaoDto;
import br.com.zupacademy.marcio.ecommerce.entities.Opiniao;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

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
    public void adicionaOpinioes(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoDto dto, @AuthenticationPrincipal Usuario usuario  ) {

        Optional<Usuario> usuarioQueOpina = usuarioRepository.findByEmail(usuario.getEmail());
        Produto produto = produtoRepository.findById(id).get();

        if(!produto.pertenceAoUsuario(usuarioQueOpina)) {

            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Opiniao opiniao = dto.converteParaObjeto(usuarioQueOpina.get(), produto);
        opiniaoRepository.save(opiniao);
    }
}
