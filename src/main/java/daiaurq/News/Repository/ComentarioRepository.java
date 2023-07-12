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
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{
    
    @Query("SELECT c FROM Comentario c WHERE c.noticia.id = :noticiaId")
    List<Comentario> findByNoticiaId(@Param("noticiaId") int noticiaId);
    
    @Query("SELECT c FROM Comentario c WHERE c.usuario.id = :usuarioId")
    List<Comentario> findByUsuarioId(@Param("usuarioId") int usuarioId);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Comentario c WHERE c.noticia.id = :noticiaId")
    boolean existsByNoticiaId(@Param("noticiaId") int noticiaId);
}
