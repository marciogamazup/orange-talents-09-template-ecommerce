package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.PerguntaRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.ProdutoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.commons.utils.EmissorEmailGenerico;
import br.com.zupacademy.marcio.ecommerce.dto.PerguntaDto;
import br.com.zupacademy.marcio.ecommerce.entities.Pergunta;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos/perguntas/{id}")
public class PerguntaController {

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    EmissorEmailGenerico emissorEmailGenerico;

    @PostMapping
    @Transactional
    public void adicionaPerguntas(@PathVariable("id") Long id, @RequestBody @Valid PerguntaDto dto,
                                  @AuthenticationPrincipal Usuario usuario) {

        Usuario usuarioQuePergunta = usuarioRepository.findByEmail(usuario.getEmail()).get();
        Produto produto = produtoRepository.findById(id).get();

        Pergunta pergunta = dto.toModel(usuarioQuePergunta, produto);
        perguntaRepository.save(pergunta);
        emissorEmailGenerico.EnviaEmaildePerguntas(produto,pergunta);
    }
}
