package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class UsuarioDto {

    private Integer id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;
    @NotNull
    @PastOrPresent
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/YYYY HH:mm:ss")
    private LocalDateTime dataCriacao;

    @Deprecated
    public UsuarioDto(){
    }

    public UsuarioDto(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.dataCriacao = usuario.getDataCriacao();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Usuario converterParaObjeto(UsuarioRepository usuarioRepository) {

        return new Usuario(email,senha, dataCriacao);
    }

//    public static List<CategoriaDto> converter(List<Categoria> categorias) {
//        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
//    }
}
