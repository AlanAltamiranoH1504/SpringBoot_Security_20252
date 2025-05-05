package altamirano.hernandez.app_springsecurity_2025.repositories;

import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.correo =:email AND u.estado = 1")
    public Usuario findByEmail(@Param("email")String email);
}
