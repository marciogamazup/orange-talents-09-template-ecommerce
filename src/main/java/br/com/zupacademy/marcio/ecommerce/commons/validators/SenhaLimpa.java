package br.com.zupacademy.marcio.ecommerce.commons.validators;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * Representa uma senha limpa no sistema
 * @author albertoluizsouza
 *
 */
public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
        Assert.hasLength(senha, "A senha nao pode ser em branco");
        Assert.isTrue(senha.length()>=6,"A senha tem que ter no mínimo 6 caracteres");

        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }


}