package daiaurq.News.Repository;

import daiaurq.News.Entity.Noticia;
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
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

    public Optional<Noticia> findByTitulo(String titulo);

    public boolean existsByTitulo(String titulo);

    @Query("SELECT n, c.nombre FROM Noticia n INNER JOIN n.categoria c")
    List<Noticia> buscarNoticiasConCategoria();

}
