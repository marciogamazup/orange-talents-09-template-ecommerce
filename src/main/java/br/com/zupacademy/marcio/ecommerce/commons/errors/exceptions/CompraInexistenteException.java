package br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions;

public class CompraInexistenteException extends RuntimeException{

    public CompraInexistenteException() {
        super("Essa compra não existe na base de dados !!");
    }
}
