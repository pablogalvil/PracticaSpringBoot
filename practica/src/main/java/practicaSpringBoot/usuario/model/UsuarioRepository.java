package practicaSpringBoot.usuario.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT * FROM usuario", nativeQuery = true)
	List<Usuario> findAllUsuarios();

}
