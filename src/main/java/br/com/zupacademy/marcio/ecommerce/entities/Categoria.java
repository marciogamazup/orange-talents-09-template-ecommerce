package br.com.zupacademy.marcio.ecommerce.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Categoria categoriaMae;

    @Deprecated
    public Categoria(){
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(@NotBlank String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoriaMae=" + categoriaMae +
                '}';
    }
}
