
package daiaurq.News.Service;

import daiaurq.News.Entity.Categoria;
import daiaurq.News.Repository.CategoriaRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daiau
 */
@Service
@Transactional
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> listaCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getOne(int id){    
        return categoriaRepository.getOne(id);
    }
    
    public void saveCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void deleteNoticia(int id){
        categoriaRepository.deleteById(id);
    }
     
    public boolean existsById(int id){
       return categoriaRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }
     
    public List<Categoria> listaCategoriasVacias() {
        return categoriaRepository.findEmptyCategories();
    }   
    
    public List<?> findCantidadNoticiasByCategoria() {
        return categoriaRepository.findCantidadNoticiasByCategoria();
    }
}
 