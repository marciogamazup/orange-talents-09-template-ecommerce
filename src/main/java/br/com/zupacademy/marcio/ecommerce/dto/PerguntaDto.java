package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.entities.Pergunta;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaDto {

    @NotBlank
    private String titulo;

    public PerguntaDto(){
    }

    public  PerguntaDto(@NotBlank String titulo){
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Pergunta toModel(@NotNull @Valid Usuario usuarioQuePergunta, @NotNull @Valid Produto produto) {
        return new Pergunta(titulo,usuarioQuePergunta, produto);
    }
}
