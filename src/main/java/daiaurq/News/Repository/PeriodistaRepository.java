package daiaurq.News.Repository;

import daiaurq.News.Entity.Periodista;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daiau
 */
public interface PeriodistaRepository extends JpaRepository<Periodista, Integer>{
    
    boolean existsByNombreUsuario(String nombreUsuario);
    
    boolean existsByEmail(String email);
    
    Optional <Periodista> findByNombreUsuario(String nombreUsuario);
    
    Optional <Periodista> findByEmail(String email);
    
    Periodista getOne(int id);  
}
