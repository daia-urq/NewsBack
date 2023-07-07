package daiaurq.News.Security.Repository;

import daiaurq.News.Security.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
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
    
}