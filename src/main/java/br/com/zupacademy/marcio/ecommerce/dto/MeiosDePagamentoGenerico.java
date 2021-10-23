package br.com.zupacademy.marcio.ecommerce.dto;

import br.com.zupacademy.marcio.ecommerce.Repository.ComprasRepository;
import br.com.zupacademy.marcio.ecommerce.entities.Pagamentos;

public interface MeiosDePagamentoGenerico {

    Pagamentos toModel(ComprasRepository comprasRepository);

}
