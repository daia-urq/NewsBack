package daiaurq.News.Service;

import daiaurq.News.Entity.Comentario;
import daiaurq.News.Repository.ComentarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daiau
 */
@Service
@Transactional
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;
    
    public List<Object[]> listaComentarioPorNoticia(int id){
        return comentarioRepository.findByNoticiaId(id);
    }
    
    public List<?> listaComentarioPorUsuario(int id){
        return comentarioRepository.findByUsuarioId(id);
    }
    
    public Optional<Comentario> findById(int id){    
        return comentarioRepository.findById(id);
    }
    
    public void saveComentario(Comentario comentario){
        comentarioRepository.save(comentario);
    }
    
    public void deleteComentario(int id){
        comentarioRepository.deleteById(id);
    }
        
    public boolean existsById(int id){
       return comentarioRepository.existsById(id);
    }
    
    public boolean existsByNoticiaId(int id){
        return comentarioRepository.existsByNoticiaId(id);
    }
    
    public boolean existsByUsuarioId(int id){
        return comentarioRepository.existsByUsuarioId(id);
    }
}
