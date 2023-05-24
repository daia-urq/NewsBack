 package daiaurq.News.Service;

import daiaurq.News.Entity.Noticia;
import daiaurq.News.Repository.NoticiaRepository;
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
public class NoticiaService {
    @Autowired
    NoticiaRepository noticiaRepository;
    
    public List<Noticia> listaNoticias(){
        return noticiaRepository.buscarNoticiasConCategoria();
    }
    
    public Optional<Noticia> getOne(int id){    
        return noticiaRepository.findById(id);
    }
    
    public void saveNoticia(Noticia noticia){
        noticiaRepository.save(noticia);
    }
    
    public void deleteNoticia(int id){
        noticiaRepository.deleteById(id);
    }
        
    public boolean existsById(int id){
       return noticiaRepository.existsById(id);
    }
    
    public boolean existsByTitulo(String titulo){
        return noticiaRepository.existsByTitulo(titulo);                
    }    
        
    public Optional<Noticia>getByTitulo(String titulo){
        return noticiaRepository.findByTitulo(titulo);
    }   
}