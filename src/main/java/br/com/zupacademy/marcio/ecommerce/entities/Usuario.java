package br.com.zupacademy.marcio.ecommerce.entities;

import javax.persistence.*;
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
    @Size(min = 6)
    private String senha;
    @NotNull
    @PastOrPresent
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/YYYY HH:mm:ss")
    private LocalDateTime dataCriacao;

    @Deprecated
    public Usuario(){
    }

    public Usuario(@NotBlank @Email String email, @NotBlank @Size(min = 6) String senha, @NotNull @PastOrPresent LocalDateTime dataCriacao) {
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
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

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
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
