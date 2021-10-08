package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.CategoriaRepository;
import br.com.zupacademy.marcio.ecommerce.dto.CategoriaDto;
import br.com.zupacademy.marcio.ecommerce.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Categoria> cadastrar(@RequestBody @Valid CategoriaDto dto){

        Categoria categoria = dto.converteParaObjeto();

        categoriaRepository.save(categoria);

        return  ResponseEntity.ok(categoria);
    }
}
