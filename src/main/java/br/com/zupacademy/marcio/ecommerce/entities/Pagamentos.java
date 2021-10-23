package br.com.zupacademy.marcio.ecommerce.entities;

import br.com.zupacademy.marcio.ecommerce.entities.enums.StatusDePagamento;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_pagamentos")
public class Pagamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String idPagamento = UUID.randomUUID().toString().replace("-","");

    private final LocalDateTime momentoDoPagamento = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDePagamento statusDePagamento;

    @ManyToOne
    @JoinColumn(name = "compras_id", nullable = false)
    private Compras compras;

    @Deprecated
    public Pagamentos() {
    }

    public Pagamentos(StatusDePagamento statusDePagamento, Compras compras) {
        this.statusDePagamento = statusDePagamento;
        this.compras = compras;
    }

    public StatusDePagamento getStatusDePagamento() {
        return statusDePagamento;
    }

    public Compras getCompras() {
        return compras;
    }

    public String getIdPagamento() {
        return idPagamento;
    }
}
