package br.com.zupacademy.marcio.ecommerce.commons.utils;

import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Pagamentos;
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

    @Override
    public void EnviaEmailPagamentoComSucesso(Compras compras, Pagamentos pagamentos) {

        String comprador = compras.getUsuario().getUsername();

        System.out.println("Email:");
        System.out.println("Remetente: administrativo@mercadolivre.com");
        System.out.println("Assunto: Conclusão da compra do produto escolhido");
        System.out.println("Prezado "+comprador+", a compra do produto escolhido ("+
                compras.getProduto().getNome()+"), foi concluída com sucesso. O Sr(a). pagou "+
                        "o valor de R$ "+
                compras.getPreco().toString()+" e foi pago através do: "+
                compras.getGatewayDePagamento().name() + "e será entregue pela transportadora "+
                        "Nunca Entregaremos Transportadora Ltda. nos próximos dias.");
        System.out.println("Id de Compra   : "+compras.getId());
        System.out.println("Id de Transação: "+pagamentos.getIdPagamento());
    }


    @Override
    public void EnviaEmailPagamentoComErro(Compras compras, Pagamentos pagamentos) {

        String comprador = compras.getUsuario().getUsername();

        System.out.println("Email:");
        System.out.println("Remetente: administrativo@mercadolivre.com");
        System.out.println("Assunto: Erro no pagamento da compra do produto escolhido");
        System.out.println("Prezado "+comprador+", o pagamento da compra do produto escolhido ("+
                compras.getProduto().getNome()+"), N Ã O  foi concluída com sucesso. ");
        System.out.println("Id de Compra   : "+compras.getId());
        System.out.println("Id de Transação: "+pagamentos.getIdPagamento());
    }


}
