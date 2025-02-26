package br.com.zupacademy.marcio.ecommerce.Repository;

import br.com.zupacademy.marcio.ecommerce.entities.Categoria;
import br.com.zupacademy.marcio.ecommerce.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(Integer id);
}
