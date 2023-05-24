//package daiaurq.News.Controller;
//
//import daiaurq.News.Entity.Mensaje;
//import daiaurq.News.Entity.Usuario;
//import daiaurq.News.Service.UsuarioService;
//import daiaurq.News.dto.dtoUsuario;
//import java.util.Date;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * @author daiau
// */
//@RestController
//@RequestMapping("/usuario")
//public class UsuarioController {
//
//    @Autowired
//    UsuarioService usuarioService;
//
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody dtoUsuario dtoUsu) {
//        if (StringUtils.isBlank(dtoUsu.getNombreUsuario())) {
//            return new ResponseEntity( new Mensaje("Debe ingresar un nombre de ususario"),HttpStatus.BAD_REQUEST );
//        }
//        
//        if (StringUtils.isBlank(dtoUsu.getPassword())) {
//            return new ResponseEntity( new Mensaje("Debe ingresar un password"),HttpStatus.BAD_REQUEST );
//        }
//        
//        if (usuarioService.existsByNombreUsuario(dtoUsu.getNombreUsuario())) {
//            return new ResponseEntity(new Mensaje("Ya existe este nombre de usuario"), HttpStatus.BAD_REQUEST);
//        }
//                 
//        Usuario usuario  = new Usuario(dtoUsu.getNombreUsuario(), dtoUsu.getPassword(),dtoUsu.getEmail());
//        usuarioService.saveUsuario(usuario);
//        
//        return new ResponseEntity(new Mensaje("Usuario creado"), HttpStatus.OK);
//    }
//    
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?>delete(@PathVariable("id")int id){
//        if (!usuarioService.existsById(id)){
//            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
//        }        
//        usuarioService.deleteUsuario(id);    
//        return new ResponseEntity(new Mensaje("Usuario eliminado"),HttpStatus.OK);
//    }
//    
//     @PutMapping("/update/{id}")
//    public ResponseEntity<?> upDate(@PathVariable("id") int id, @RequestBody dtoUsuario dtoUsu) {
//        if (!usuarioService.existsById(id)) {
//            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
//        }
//
//        if (usuarioService.existsByNombreUsuario(dtoUsu.getNombreUsuario()) && usuarioService.getByNombreUsuario(dtoUsu.getNombreUsuario()).get().getId() != id) {
//            return new ResponseEntity(new Mensaje("Este nombre de usuario ya existe"), HttpStatus.BAD_GATEWAY);
//        }
//
//        if (StringUtils.isBlank(dtoUsu.getPassword())) {
//            return new ResponseEntity(new Mensaje("Ingrese un nuevo password"), HttpStatus.BAD_REQUEST);
//        }
//         //probar si los password son iguales
//        
//        Usuario usuario = usuarioService.getOne(id).get();
//        
//        usuario.setNombreUsuario(dtoUsu.getNombreUsuario());
//        usuario.setPassword(dtoUsu.getPassword());
//        
//        usuarioService.saveUsuario(usuario);
//        
//        return new ResponseEntity(new Mensaje( "usuario modificado"), HttpStatus.OK);
//    }
//}
