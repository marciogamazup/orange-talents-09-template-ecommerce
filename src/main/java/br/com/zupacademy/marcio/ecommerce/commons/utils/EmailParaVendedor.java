package br.com.zupacademy.marcio.ecommerce.commons.utils;

import br.com.zupacademy.marcio.ecommerce.entities.Pergunta;
import br.com.zupacademy.marcio.ecommerce.entities.Produto;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EmailParaVendedor {

    public void avisaVendedor(String vendedor, String produto, String valor, String meioPagamento) {

        System.out.println("Email:");
        System.out.println("Remetente: administrativo@mercadolivre.com");
        System.out.println("Assunto: Venda do seu produto");
        System.out.println("Prezado "+vendedor+", o seu produto "+produto+" foi vendido pelo valor de R$ "+valor+" e foi pago através do: "+meioPagamento);
    }
}
