package daiaurq.News.Repository;

import daiaurq.News.Entity.Periodista;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author daiau
 */
public interface PeriodistaRepository extends JpaRepository<Periodista, Integer> {

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);

    Optional<Periodista> findByNombreUsuario(String nombreUsuario);

    Optional<Periodista> findByEmail(String email);

    Periodista findById(int id);

    @Query("SELECT p.id FROM Periodista p " +
           "LEFT JOIN p.noticias n " +
           "WHERE TYPE(p) = Periodista " +
           "GROUP BY p.id " +
           "HAVING COUNT(n) = 0")
    List<Integer> findIdPeriodistaSinNoticias();

    
}
