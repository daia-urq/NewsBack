package daiaurq.News.Repository;

import daiaurq.News.Entity.Noticia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daiau
 */
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer>  {
    
    public Optional<Noticia> findByTitulo(String titulo);
    
    public boolean existsByTitulo(String titulo);
}
