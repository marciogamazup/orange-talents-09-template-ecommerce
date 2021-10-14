package br.com.zupacademy.marcio.ecommerce.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_opiniao")
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Integer nota;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produtoOpniao;

    @Deprecated
    public Opiniao(){
    }

    public Opiniao(String titulo, String descricao, Integer nota, Usuario usuario, Produto produtoOpniao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.usuario = usuario;
        this.produtoOpniao = produtoOpniao;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", nota=" + nota +
                ", usuario=" + usuario +
                ", produto=" + produtoOpniao +
                '}';
    }
}
