package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.dto.UsuarioDto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastrar (@RequestBody @Valid UsuarioDto dto){

        Usuario usuario = dto.converterParaObjeto(usuarioRepository);

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);
    }
}
