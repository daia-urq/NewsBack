package daiaurq.News.Security.Repository;

import daiaurq.News.Security.Entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daiau
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Optional <Usuario> findByNombreUsuario(String nombreUsuario);
    
    Optional <Usuario> findByEmail(String email);
    
    boolean existsByNombreUsuario(String nombreUsuario);
    
    boolean existsByEmail(String email);
    
    Usuario getOne(int id);  
        
     @Query("SELECT u " +
           "FROM Usuario u " +
           "INNER JOIN u.roles r " +
           "WHERE r.id = 1")
     List<Usuario> findUsuariosByRolId();
}