package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.ProdutoRepository;
import br.com.zupacademy.marcio.ecommerce.dto.ListaProdutoDetalhadoDto;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DetalheProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping(value = "/produtos/{id}")
    public ResponseEntity<ListaProdutoDetalhadoDto> listaProdutoDetalhado (@PathVariable Long id) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if (!produto.isPresent()) {
                return ResponseEntity.notFound().build();
        }

        ListaProdutoDetalhadoDto listaProdutoDetalhadoDto = new ListaProdutoDetalhadoDto(produto.get());
        return ResponseEntity.ok(listaProdutoDetalhadoDto);

    }
}
