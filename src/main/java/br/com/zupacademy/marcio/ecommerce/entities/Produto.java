package br.com.zupacademy.marcio.ecommerce.entities;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    //    @Type(type = "materialized_nclob")
    private String descricao;

    @Column(nullable = false)
    private Integer quantidade;

    @PastOrPresent
    private LocalDate dataCadastro = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ElementCollection
    private Map<String, String> caracteristicas = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Deprecated
    public Produto(){
    }

    public Produto(String nome, BigDecimal preco, String descricao, Integer quantidade, Categoria categoria,
             Usuario usuario, Map<String, String> caracteristicas) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.dataCadastro = LocalDate.now();
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas = caracteristicas;
    }
 }
