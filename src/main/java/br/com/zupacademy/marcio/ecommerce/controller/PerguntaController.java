package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.PerguntaRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.ProdutoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.commons.utils.EnviaEmailFake;
import br.com.zupacademy.marcio.ecommerce.dto.PerguntaDto;
import br.com.zupacademy.marcio.ecommerce.entities.Pergunta;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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
    EnviaEmailFake enviaEmailFake;

    @PostMapping
    @Transactional
    public void adicionaPerguntas(@PathVariable("id") Long id, @RequestBody @Valid PerguntaDto dto) {

        Usuario usuarioQuePergunta = usuarioRepository.findByEmail("marciogama@gmail.com").get();
        Produto produto = produtoRepository.findById(id).get();

        Pergunta pergunta = dto.toModel(usuarioQuePergunta, produto);
        perguntaRepository.save(pergunta);
        enviaEmailFake.enviandoEmail(produto, pergunta);
    }
}
