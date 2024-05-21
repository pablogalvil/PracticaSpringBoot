package practicaSpringBoot.usuario.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT * FROM usuario u WHERE u.peso > :peso")
	List<Usuario> buscarPorPeso(@Param("peso") double peso);

}
