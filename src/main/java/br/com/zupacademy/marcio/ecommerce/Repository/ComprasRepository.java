package br.com.zupacademy.marcio.ecommerce.Repository;

import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprasRepository extends JpaRepository<Compras, Long> {
}
