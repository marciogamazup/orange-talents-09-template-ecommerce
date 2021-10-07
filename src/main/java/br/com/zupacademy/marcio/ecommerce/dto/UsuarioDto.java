package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import br.com.zupacademy.marcio.ecommerce.commons.validators.SenhaLimpa;
import br.com.zupacademy.marcio.ecommerce.commons.validators.UniqueValue;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class UsuarioDto {

    private Integer id;
    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;
    @NotNull
    @PastOrPresent
    private LocalDateTime dataCriacao;

    @Deprecated
    public UsuarioDto(){
    }

    public UsuarioDto(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
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
        return new Usuario(email, new SenhaLimpa(senha));
    }
}
