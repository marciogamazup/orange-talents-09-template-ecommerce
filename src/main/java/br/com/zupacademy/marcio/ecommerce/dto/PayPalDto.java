package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.Repository.ComprasRepository;
import br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions.CompraInexistenteException;
import br.com.zupacademy.marcio.ecommerce.commons.validators.UniqueValue;
import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Pagamentos;
import br.com.zupacademy.marcio.ecommerce.entities.enums.StatusDePagamento;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PayPalDto implements MeiosDePagamentoGenerico{

    @NotNull
    private Long compraId;

    @NotNull
    @Min(0)
    @Max(1)
    private int statusPagamento;

    @UniqueValue(domainClass = Pagamentos.class, fieldName = "idPagamento")
    private String idPagamento;

    @Deprecated
    public PayPalDto() {
    }

    public PayPalDto(Long compraId, int statusPagamento) {
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

    public int getStatusPagamento() {
        return statusPagamento;
    }

    @Override
    public Pagamentos toModel(ComprasRepository comprasRepository) {

        Compras compras = comprasRepository.findById(compraId).orElseThrow(CompraInexistenteException::new);
        StatusDePagamento status = this.statusPagamento==1 ? StatusDePagamento.SUCESSO :
                                                            StatusDePagamento.ERRO;
        return new Pagamentos(status,compras);
    }
}
