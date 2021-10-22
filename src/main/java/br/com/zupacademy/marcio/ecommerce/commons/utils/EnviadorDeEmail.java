package br.com.zupacademy.marcio.ecommerce.commons.utils;

import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Pergunta;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmail implements EmissorEmailGenerico {

    @Override
    public void EnviaEmaildePerguntas(Produto produto, Pergunta pergunta) {

        System.out.println("Email enviado para o vendedor: "+ produto.getUsuario().getUsername());
        System.out.println("Pergunta: "+pergunta.getTitulo());
        System.out.println("Prduto: "+produto.getNome());
    }

    @Override
    public void EnviaEmailParaVendedor(Produto produto, Compras compras) {

        String vendedor = produto.getUsuario().getUsername();


        System.out.println("Email:");
        System.out.println("Remetente: administrativo@mercadolivre.com");
        System.out.println("Assunto: Venda do seu produto");
        System.out.println("Prezado "+produto.getUsuario().getUsername()+", o seu produto "+
                                        produto.getNome()+" foi vendido pelo valor de R$ "+
                                        compras.getPreco().toString()+" e foi pago através do: "+
                                        compras.getGatewayDePagamento().name());
    }
}
