package daiaurq.News.Repository;

import daiaurq.News.Entity.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daiau
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    boolean existsByNombre(String nombre);

    Categoria getOne(int id);

    @Query("SELECT c FROM Categoria c WHERE NOT EXISTS (SELECT n FROM Noticia n WHERE n.categoria.id = c.id)")
    List<Categoria> findEmptyCategories();

    @Query("SELECT c.nombre, COUNT(n.categoria.id) AS cantidadNoticias FROM Categoria c LEFT JOIN c.noticias n GROUP BY c.nombre")
    List<?> findCantidadNoticiasByCategoria();
}
