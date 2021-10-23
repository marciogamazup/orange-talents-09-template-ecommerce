package br.com.zupacademy.marcio.ecommerce.commons.utils;

import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import br.com.zupacademy.marcio.ecommerce.entities.Pagamentos;
import org.springframework.stereotype.Component;

@Component
public class ComunicaPagamentoAoSetorFiscal {

    public void libera(Compras compras, Pagamentos pagamentos) {

        System.out.println("Comunicado: ");
        System.out.println("Liberado para faturamento da compra de id: "+compras.getId()+
                            " do cliente "+compras.getUsuario().getId());
        System.out.println("Id de Transação: "+pagamentos.getIdPagamento());
    }
}
