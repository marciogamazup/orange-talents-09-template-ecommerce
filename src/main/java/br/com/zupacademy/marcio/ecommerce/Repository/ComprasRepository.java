package br.com.zupacademy.marcio.ecommerce.Repository;

import br.com.zupacademy.marcio.ecommerce.entities.Categoria;
import br.com.zupacademy.marcio.ecommerce.entities.Compras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComprasRepository extends JpaRepository<Compras, Long> {
   Optional<Compras> findById(Long id);
}