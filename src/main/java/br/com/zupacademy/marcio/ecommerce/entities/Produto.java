package br.com.zupacademy.marcio.ecommerce.entities;

import br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions.EstoqueInsuficienteException;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Pergunta> perguntas = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Opiniao> opinioes = new ArrayList<>();

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

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public void associaImagens(Set<String> linksDaLista) {
        Set<ImagemProduto> imagensDoProduto = linksDaLista.stream().map(links -> new ImagemProduto(this,links))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagensDoProduto);
    }

    public boolean pertenceAoUsuario(Usuario possivelDono) {

        return this.usuario.equals(possivelDono);
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Integer contadorDeNotas() {

        List<Opiniao> opinioes = this.getOpinioes();

        return opinioes.size();
    }

    public Double mediaDeNotas() {

        List<Opiniao> opinioes = this.getOpinioes();
        List<Integer> notas = opinioes.stream().map(Opiniao::getNota).collect(Collectors.toList());

        return  notas.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    public void verificarAQuantidadeDoProdutoNoEstoque(Integer quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero !!");
        if (quantidade > this.quantidade) {
            throw new EstoqueInsuficienteException();
        }
    }
}
