package br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions;

public class EstoqueInsuficienteException extends RuntimeException{

    public EstoqueInsuficienteException() {
        super("Estoque insuficiente para essa operação !");
    }
}
