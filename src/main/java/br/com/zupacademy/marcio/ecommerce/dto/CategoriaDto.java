package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.commons.validators.UniqueValue;
import br.com.zupacademy.marcio.ecommerce.entities.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CategoriaDto {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    @JsonProperty("nome")  // permite que o cadastro da primeira categoriaMae seja nula
    private String nome;

    @JsonProperty("categoriaMae")
    private CategoriaMaeDto categoriaMae;

    @Deprecated
    public CategoriaDto() {
    }

    public CategoriaMaeDto getCategoriaMae() {
        return categoriaMae;
    }

    public Categoria converteParaObjeto() {
        if (categoriaMae==null){
            return new Categoria(nome);
        }

        return new Categoria(nome,categoriaMae.converteParaObjeto());
    }
}
