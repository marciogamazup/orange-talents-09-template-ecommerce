package br.com.zupacademy.marcio.ecommerce.entities;

import br.com.zupacademy.marcio.ecommerce.entities.enums.GatewayDePagamento;
import br.com.zupacademy.marcio.ecommerce.entities.enums.StatusDeCompra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
