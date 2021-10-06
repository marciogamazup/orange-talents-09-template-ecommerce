package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.dto.UsuarioDto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastrar (@RequestBody @Valid UsuarioDto dto){


        Usuario usuario = dto.converterParaObjeto(usuarioRepository);

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);
    }
}
