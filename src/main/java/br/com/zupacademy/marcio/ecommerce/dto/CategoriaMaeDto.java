package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.commons.validators.ExistId;
import br.com.zupacademy.marcio.ecommerce.entities.Categoria;

public class CategoriaMaeDto {

    @ExistId(domainClass= Categoria.class,fieldName = "id")
    private Integer id;

    @Deprecated
    public CategoriaMaeDto(){
    }

    public CategoriaMaeDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Categoria converteParaObjeto() {
        return new Categoria(id);
    }
}
