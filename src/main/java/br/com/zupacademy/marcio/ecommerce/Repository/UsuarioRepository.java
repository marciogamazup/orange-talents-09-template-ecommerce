package br.com.zupacademy.marcio.ecommerce.Repository;

import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
