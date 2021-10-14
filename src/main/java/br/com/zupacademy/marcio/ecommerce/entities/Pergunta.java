package br.com.zupacademy.marcio.ecommerce.entities;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pergunta")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produtoPergunta;

    @Deprecated
    public Pergunta(){
    }

    public Pergunta(String titulo, Usuario usuario, Produto produtoPergunta ){
        this.titulo = titulo;
        this.dataCriacao = LocalDateTime.now();
        this.usuario = usuario;
        this.produtoPergunta = produtoPergunta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", usuario=" + usuario +
                ", produtoPergunta=" + produtoPergunta +
                '}';
    }
}
