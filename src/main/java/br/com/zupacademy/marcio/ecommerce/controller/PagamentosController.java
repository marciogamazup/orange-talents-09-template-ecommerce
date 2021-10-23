package br.com.zupacademy.marcio.ecommerce.controller;

import br.com.zupacademy.marcio.ecommerce.Repository.ComprasRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.PagamentosRepository;
import br.com.zupacademy.marcio.ecommerce.commons.utils.ComunicaPagamentoAoRankingVendedores;
import br.com.zupacademy.marcio.ecommerce.commons.utils.ComunicaPagamentoAoSetorFiscal;
import br.com.zupacademy.marcio.ecommerce.commons.utils.EnviadorDeEmail;
import br.com.zupacademy.marcio.ecommerce.dto.PagSeguroDto;
import br.com.zupacademy.marcio.ecommerce.dto.PayPalDto;
import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Pagamentos;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import br.com.zupacademy.marcio.ecommerce.entities.enums.StatusDePagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentosController {

    @Autowired
    PagamentosRepository pagamentosRepository;

    @Autowired
    ComprasRepository comprasRepository;

    @Autowired
    ComunicaPagamentoAoSetorFiscal comunicaPagamentoAoSetorFiscal;

    @Autowired
    ComunicaPagamentoAoRankingVendedores comunicaPagamentoAoRankingVendedores;

    @Autowired
    EnviadorDeEmail enviadorDeEmail;

    @PostMapping("/pagseguro")
    @Transactional
    public void cadastrarPagSeguro(@RequestBody @Valid PagSeguroDto dto,
                                   @AuthenticationPrincipal Usuario usuario) {

        Pagamentos pagamentos = dto.toModel(comprasRepository);
        Compras compras = pagamentos.getCompras();

        avaliaCondicoesDoPagamento(compras,pagamentos);
    }

    @PostMapping("/paypal")
    @Transactional
    public void cadastrarPayPal(@RequestBody @Valid PayPalDto dto,
                                @AuthenticationPrincipal Usuario usuario) {

        Pagamentos pagamentos = dto.toModel(comprasRepository);

        Compras compras = pagamentos.getCompras();

        avaliaCondicoesDoPagamento(compras,pagamentos);
    }

    private void avaliaCondicoesDoPagamento(Compras compras, Pagamentos pagamentos) {
        compras.achaPagamentoCadastradoComSucesso();
        pagamentosRepository.save(pagamentos);
        if(pagamentos.getStatusDePagamento().equals(StatusDePagamento.SUCESSO)) {
            comunicaPagamentoAoSetorFiscal.libera(compras, pagamentos);
            comunicaPagamentoAoRankingVendedores.libera(compras, pagamentos);
            compras.alteraStatusPagamento();
            enviadorDeEmail.EnviaEmailPagamentoComSucesso(compras,pagamentos);
        } else {
            enviadorDeEmail.EnviaEmailPagamentoComErro(compras,pagamentos);
        }
    }
}
