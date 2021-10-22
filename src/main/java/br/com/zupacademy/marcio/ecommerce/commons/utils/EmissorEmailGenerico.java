package br.com.zupacademy.marcio.ecommerce.commons.utils;

import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Pergunta;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;

public interface EmissorEmailGenerico {

    void EnviaEmailParaVendedor(Produto produto, Compras compras);

    void EnviaEmaildePerguntas(Produto produto, Pergunta pergunta);

}
