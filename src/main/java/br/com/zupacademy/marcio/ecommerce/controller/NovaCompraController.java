package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.ComprasRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.ProdutoRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions.ProdutoInexistenteException;
import br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions.UsuarioInexistenteException;
import br.com.zupacademy.marcio.ecommerce.commons.utils.EmailParaVendedor;
import br.com.zupacademy.marcio.ecommerce.dto.NovaCompraDto;
import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import br.com.zupacademy.marcio.ecommerce.entities.enums.GatewayDePagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovaCompraController {

    @Autowired
    ComprasRepository comprasRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmailParaVendedor emailParaVendedor;

    @PostMapping("/compras")
    @Transactional
    public ResponseEntity<?> comprar(@RequestBody @Valid NovaCompraDto dto,
                                     UriComponentsBuilder uriComponentsBuilder)  {

        Produto produto = produtoRepository.findById(dto.getIdProduto()).orElseThrow(ProdutoInexistenteException::new);

        Usuario usuarioQueCompra = usuarioRepository.findByEmail(dto.getEmailComprador()).orElseThrow(UsuarioInexistenteException::new);

        produto.verificarAQuantidadeDoProdutoNoEstoque(dto.getQuantidade());

        Compras compras = dto.toModel(produto, usuarioQueCompra);

        comprasRepository.save(compras);
        emailParaVendedor.avisaVendedor(produto.getUsuario().getUsername(), produto.getNome(),
                        compras.getPreco().toString(), compras.getGatewayDePagamento().name());

        URI meioPagamentoUri = URI.create((compras.getGatewayDePagamento().equals(GatewayDePagamento.PAGSEGURO)) ?
                ("pagseguro.com/" + compras.getId() + "?redirectUrl=" + uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(compras.getId()).toString()) :
                ("paypal.com/" + compras.getId() + "?redirectUrl=" + uriComponentsBuilder.path("/retorno-paypal/{id}")
                        .buildAndExpand(compras.getId()).toString()));

        return ResponseEntity.status(HttpStatus.FOUND).body(meioPagamentoUri);
    }
}