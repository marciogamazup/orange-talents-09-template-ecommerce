package br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions;

public class ExceptionPagamentoJaEfetuado extends RuntimeException {
    public ExceptionPagamentoJaEfetuado(String msg) {
        super(msg);
    }
}
