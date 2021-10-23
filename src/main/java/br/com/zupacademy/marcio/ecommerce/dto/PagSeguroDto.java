package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.Repository.ComprasRepository;
import br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions.CompraInexistenteException;
import br.com.zupacademy.marcio.ecommerce.commons.validators.UniqueValue;
import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Pagamentos;
import br.com.zupacademy.marcio.ecommerce.entities.enums.StatusDePagamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroDto implements MeiosDePagamentoGenerico{

    @NotNull
    private Long compraId;

    @NotBlank
    private String statusPagamento;

    @UniqueValue(domainClass = Pagamentos.class, fieldName = "idPagamento")
    private String idPagamento;

    @Deprecated
    public PagSeguroDto() {
    }

    public PagSeguroDto(Long compraId, String statusPagamento) {
        this.idPagamento = idPagamento;
        this.compraId = compraId;
        this.statusPagamento = statusPagamento;
    }

    public String getIdPagamento() {
        return idPagamento;
    }

    public Long getCompraId() {
        return compraId;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    @Override
    public Pagamentos toModel(ComprasRepository comprasRepository) {
        Compras compras = comprasRepository.findById(compraId).orElseThrow(CompraInexistenteException::new);
        StatusDePagamento statusDePagamento = StatusDePagamento.valueOf(statusPagamento);
        return new Pagamentos(statusDePagamento,compras );
    }
}
