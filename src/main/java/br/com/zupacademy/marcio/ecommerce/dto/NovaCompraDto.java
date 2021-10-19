package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import br.com.zupacademy.marcio.ecommerce.entities.enums.GatewayDePagamento;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaCompraDto {

    @Positive
    @NotNull
    private Integer quantidade;
    @NotNull
    @Positive
    private Long idProduto;
    @NotNull
    private GatewayDePagamento gatewayDePagamento;
    @NotBlank
    @Email
    private String emailComprador;

    @Deprecated
    public NovaCompraDto(){
    }

    public NovaCompraDto(@Positive @NotNull Integer quantidade, @NotNull @Positive Long idProduto,
                         GatewayDePagamento gatewayDePagamento, @NotBlank @Email String emailComprador){
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gatewayDePagamento = gatewayDePagamento;
        this.emailComprador = emailComprador;
    }

    public Compras toModel(Produto produto, Usuario usuario) {

        BigDecimal valor = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));

        return new Compras(quantidade, valor, gatewayDePagamento, produto, usuario);

    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayDePagamento getGatewayDePagamento() {
        return gatewayDePagamento;
    }

    public String getEmailComprador() {
        return emailComprador;
    }
}


