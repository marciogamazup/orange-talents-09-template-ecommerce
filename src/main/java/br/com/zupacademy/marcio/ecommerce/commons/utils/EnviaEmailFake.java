package br.com.zupacademy.marcio.ecommerce.commons.utils;

import br.com.zupacademy.marcio.ecommerce.entities.Pergunta;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailFake {

    public void enviandoEmail (Produto produto, Pergunta pergunta) {

        String vendedor = produto.getUsuario().getUsername();
        String perg = pergunta.getTitulo();
        String prod = produto.getNome();

        System.out.println("Email enviado para o vendedor: "+ vendedor);
        System.out.println("Pergunta: "+perg);
        System.out.println("Prduto: "+prod);
    }
}
