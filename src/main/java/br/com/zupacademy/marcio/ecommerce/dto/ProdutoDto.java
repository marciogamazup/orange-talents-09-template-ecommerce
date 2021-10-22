package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.Repository.CategoriaRepository;
import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.commons.validators.ExistId;
import br.com.zupacademy.marcio.ecommerce.entities.Categoria;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProdutoDto {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ExistId(domainClass= Categoria.class, fieldName = "id")
    private Integer categoria_id;

    @NotNull
    @ExistId(domainClass = Usuario.class, fieldName = "id")
    private Integer usuario_id;

    @Size(min = 3)
    private Map<String, String> caracteristicas = new HashMap<>();

    @Deprecated
    private ProdutoDto() {
    }

    public ProdutoDto(@NotBlank String nome, BigDecimal preco, String descricao, Integer quantidade, Integer categoria_id,
                      Integer usuario_id, Map<String, String> caracteristicas) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categoria_id = categoria_id;
        this.usuario_id = usuario_id;
        this.caracteristicas = caracteristicas;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer getCategoria_id() {
        return categoria_id;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto converteParaObjeto(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> categoria = categoriaRepository.findById(categoria_id);
        return new Produto(this.nome, this.preco, this.descricao, this.quantidade,categoria.get(),usuario,caracteristicas );
    }
}
