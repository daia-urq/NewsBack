package daiaurq.News.Service;

import daiaurq.News.Entity.Periodista;
import daiaurq.News.Repository.PeriodistaRepository;
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
public class PeriodistaService {
    
    @Autowired
    PeriodistaRepository periodistaRepository;
    
    public Optional<Periodista> getByNombreUsuario(String nombreUsaurio){
        return periodistaRepository.findByNombreUsuario(nombreUsaurio);
    }
    
    public Optional<Periodista> getByEmail(String email){
        return periodistaRepository.findByEmail(email);
    }
    
    public List<Periodista> listaPeriodistas(){
        return periodistaRepository.findAll();
    }
    @Transactional
    public void savePeriodista(Periodista periodista){
        periodistaRepository.save(periodista);
    }
    @Transactional
    public void deletePeriodista(int id){
        periodistaRepository.deleteById(id);
    } 
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return periodistaRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String email){
        return periodistaRepository.existsByEmail(email);
    }   
    
    public boolean existsById(int id){
       return periodistaRepository.existsById(id);
    }
    
    public Periodista getOne(int id){
        return periodistaRepository.getOne(id);
    }
    
    public List<Integer> findIdPeriodistaSinNoticia(){
        return periodistaRepository.findIdPeriodistaSinNoticias();
    }
}
