package daiaurq.News.Security.Service;

import daiaurq.News.Security.Entity.Rol;
import daiaurq.News.Security.Enums.RolNombre;
import daiaurq.News.Security.Repository.RolRepository;
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
public class RolService {
    @Autowired
    RolRepository rolRepository;
    
    public Rol getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
    
    public boolean existsByRolNombre(String rolNombre){
        return rolRepository.existsByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        rolRepository.save(rol);
    }
}