package daiaurq.News.Repository;

import daiaurq.News.Entity.Periodista;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daiau
 */
public interface PeriodistaRepository extends JpaRepository<Periodista, Integer>{
    
    boolean existsByNombreUsuario(String nombreUsuario);
    
    boolean existsByEmail(String email);
}
