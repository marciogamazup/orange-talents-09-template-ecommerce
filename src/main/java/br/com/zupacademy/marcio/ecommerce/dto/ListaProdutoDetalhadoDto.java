package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.entities.ImagemProduto;
import br.com.zupacademy.marcio.ecommerce.entities.Opiniao;
import br.com.zupacademy.marcio.ecommerce.entities.Pergunta;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListaProdutoDetalhadoDto {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Map<String, String> caracteristicas = new HashMap<>();
    private Set<String> imagemProdutos;
    private Stream<String> perguntas;
    private Stream<String> opinioes;
    private Integer contadorDeNotas;
    private Double mediaDeNotas;

    @Deprecated
    public ListaProdutoDetalhadoDto() {
    }

    public ListaProdutoDetalhadoDto (Produto produto){
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.caracteristicas = produto.getCaracteristicas();
        this.imagemProdutos = produto.getImagens().stream()
                .map(ImagemProduto::getLink)
                .collect(Collectors.toSet());
        this.perguntas = produto.getPerguntas().stream()
                .map(Pergunta::getTitulo);
        this.opinioes = produto.getOpinioes().stream()
                .map(Opiniao::getTitulo);
        this.contadorDeNotas = produto.contadorDeNotas();
        this.mediaDeNotas = produto.mediaDeNotas();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagemProdutos() {
        return imagemProdutos;
    }

    public Stream<String> getPerguntas() {
        return perguntas;
    }

    public Stream<String> getOpinioes() {
        return opinioes;
    }

    public Integer getContadorDeNotas() {
        return contadorDeNotas;
    }

    public Double getMediaDeNotas() {
        return mediaDeNotas;
    }
}
