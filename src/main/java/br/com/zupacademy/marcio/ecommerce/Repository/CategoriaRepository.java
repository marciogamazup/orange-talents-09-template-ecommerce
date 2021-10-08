package br.com.zupacademy.marcio.ecommerce.Repository;

import br.com.zupacademy.marcio.ecommerce.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
