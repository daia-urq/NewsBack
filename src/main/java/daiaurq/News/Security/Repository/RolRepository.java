package daiaurq.News.Security.Repository;

import daiaurq.News.Security.Entity.Rol;
import daiaurq.News.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daiau
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

