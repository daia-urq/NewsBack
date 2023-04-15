package daiaurq.News.Security.Service;

import daiaurq.News.Security.Entity.Usuario;
import daiaurq.News.Security.Repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author daiau
 */
@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsaurio){
        return usuarioRepository.findByNombreUsuario(nombreUsaurio);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }    
    @Transactional
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}