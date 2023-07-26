package daiaurq.News.Security.Service;

import daiaurq.News.Security.Entity.Usuario;
import daiaurq.News.Security.Repository.UsuarioRepository;
import java.util.List;
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
    
     public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
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
    
    @Transactional
    public void delete(int id){
        usuarioRepository.deleteById(id);
    } 
    
    public boolean existsById(int id){
       return usuarioRepository.existsById(id);
    }
    
    public Usuario getOne(int id){
        return usuarioRepository.getOne(id);
    }
    
     public List<Usuario> listaUsuarios(){
        return usuarioRepository.findUsuariosByRolId();
    }
}