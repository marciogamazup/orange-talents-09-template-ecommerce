package br.com.zupacademy.marcio.ecommerce.entities;

import br.com.zupacademy.marcio.ecommerce.commons.validators.SenhaLimpa;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;
    @NotNull
    @PastOrPresent
    private LocalDateTime dataCriacao;

    @Deprecated
    public Usuario(){
    }

    public Usuario(@NotBlank @Email String email, @Valid @NotBlank SenhaLimpa senhaLimpa){
        this.email = email;
        this.senha = senhaLimpa.hash();
        this.dataCriacao = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
