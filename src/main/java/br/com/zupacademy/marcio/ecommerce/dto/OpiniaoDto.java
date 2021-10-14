package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.entities.Opiniao;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoDto {

    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;
    @Min(1)
    @Max(5)
    @NotNull
    private Integer nota;

    @Deprecated
    public  OpiniaoDto () {
    }

    public OpiniaoDto(@NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
                      @Min(1) @Max(5) @NotNull Integer nota) {

        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public Opiniao converteParaObjeto(@NotNull @Valid Usuario usuarioQueOpina,
                                      @NotNull @Valid Produto produto) {

        return new Opiniao(titulo, descricao, nota,usuarioQueOpina, produto);
    }
}
