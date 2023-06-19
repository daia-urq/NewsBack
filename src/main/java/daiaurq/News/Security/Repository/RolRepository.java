package daiaurq.News.Security.Repository;

import daiaurq.News.Security.Entity.Rol;
import daiaurq.News.Security.Enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daiau
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    
    public Rol findByRolNombre(RolNombre rolNombre);
    
    public boolean existsByRolNombre(String rolNombre);
}

