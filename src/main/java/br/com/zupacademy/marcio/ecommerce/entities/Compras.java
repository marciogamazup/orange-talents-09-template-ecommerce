package br.com.zupacademy.marcio.ecommerce.entities;

import br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions.ExceptionPagamentoJaEfetuado;
import br.com.zupacademy.marcio.ecommerce.entities.enums.GatewayDePagamento;
import br.com.zupacademy.marcio.ecommerce.entities.enums.StatusDeCompra;
import br.com.zupacademy.marcio.ecommerce.entities.enums.StatusDePagamento;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_compras")
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GatewayDePagamento gatewayDePagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDeCompra statusDeCompra;

    @ManyToOne
    @NotNull
    @Valid
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @NotNull
    @Valid
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "compras")
    private List<Pagamentos> pagamentos = new ArrayList<>();

    private LocalDateTime momentoCompra = LocalDateTime.now();

    @Deprecated
    public Compras() {
    }

    public Compras(@Positive Integer quantidade, BigDecimal preco,
                   GatewayDePagamento gatewayDePagamento,
                   @NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
        this.quantidade = quantidade;
        this.preco = produto.getPreco();
        this.gatewayDePagamento = gatewayDePagamento;
        this.statusDeCompra = StatusDeCompra.INICIADA;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public GatewayDePagamento getGatewayDePagamento() {
        return gatewayDePagamento;
    }

    public StatusDeCompra getStatusDeCompra() {
        return statusDeCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getMomentoCompra() {
        return momentoCompra;
    }

    public URI montaUri (UriComponentsBuilder uriComponentsBuilder, Compras compras) {

        URI meioPagamentoUri = URI.create((compras.getGatewayDePagamento().equals(GatewayDePagamento.PAGSEGURO)) ?
                ("pagseguro.com/" + compras.getId() + "?redirectUrl=" + uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(compras.getId()).toString()) :
                ("paypal.com/" + compras.getId() + "?redirectUrl=" + uriComponentsBuilder.path("/retorno-paypal/{id}")
                        .buildAndExpand(compras.getId()).toString()));

        return meioPagamentoUri;
    }

    public boolean achaPagamentoCadastradoComSucesso() {

        if(pagamentos.isEmpty()){
            return false;
        }
        for (Pagamentos pagamento :pagamentos) {
            if(pagamento.getStatusDePagamento().equals(StatusDePagamento.SUCESSO)){
                throw new ExceptionPagamentoJaEfetuado("Pagamento já efetuado");
            }
        }
        return false;
    }

    public void alteraStatusPagamento() {
        statusDeCompra = StatusDeCompra.PAGO;
    }


    @Override
    public String toString() {
        return "Compras{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                ", gatewayDePagamento=" + gatewayDePagamento +
                ", statusDeCompra=" + statusDeCompra +
                ", produto=" + produto +
                ", usuario=" + usuario +
                ", momentoCompra=" + momentoCompra +
                '}';
    }
}
