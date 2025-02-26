package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.CategoriaRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.ProdutoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.commons.utils.Uploader;
import br.com.zupacademy.marcio.ecommerce.dto.NovasImagensDto;
import br.com.zupacademy.marcio.ecommerce.dto.ProdutoDto;
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
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private Uploader uploaderFake;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProdutoDto dto, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = dto.converteParaObjeto(categoriaRepository, usuario);
        produtoRepository.save(produto);
    }

    @PostMapping(value = "/imagens/{id}")
    @Transactional
    public String adicionarImagens(@PathVariable("id") Long id, @Valid NovasImagensDto dto, @AuthenticationPrincipal Usuario usuario) {

        Optional<Usuario> dono = usuarioRepository.findByEmail(usuario.getEmail());
        Produto produto = produtoRepository.findById(id).get();

        if(!produto.pertenceAoUsuario(dono)) {

            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(dto.getImagens());

        produto.associaImagens(links);

        produtoRepository.save(produto);

        return  produto.toString();
    }
}
