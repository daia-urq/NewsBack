package daiaurq.News.Repository;

import daiaurq.News.Entity.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daiau
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    @Query("SELECT c.id, c.comentario, c.fechaCreacion, u.nombreUsuario "
            + "FROM Comentario c "
            + "INNER JOIN c.usuario u "
            + "INNER JOIN c.noticia n "
            + "WHERE n.id = :noticiaId")
    List<Object[]> findByNoticiaId(@Param("noticiaId") int noticiaId);
    
    @Query("SELECT c.id, c.comentario, c.fechaCreacion, n.titulo "
            + "FROM Comentario c "
            + "INNER JOIN c.usuario u "
            + "INNER JOIN c.noticia n "
            + "WHERE u.id = :usuarioId")
    List<Object[]> findByUsuarioId(@Param("usuarioId") int noticiaId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Comentario c WHERE c.noticia.id = :noticiaId")
    boolean existsByNoticiaId(@Param("noticiaId") int noticiaId);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Comentario c WHERE c.usuario.id = :usuarioId")
    boolean existsByUsuarioId(@Param("usuarioId") int usuarioId);
}
