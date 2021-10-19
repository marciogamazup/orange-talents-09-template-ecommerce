package br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions;

public class UsuarioInexistenteException extends RuntimeException{

    public UsuarioInexistenteException (){
        super("Usuário não está cadastrado !!");
    }
}
