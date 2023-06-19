package daiaurq.News.Controller;

import daiaurq.News.Entity.Mensaje;
import daiaurq.News.Entity.Noticia;
import daiaurq.News.Entity.Periodista;
import daiaurq.News.Security.Entity.Rol;
import daiaurq.News.Security.Enums.RolNombre;
import daiaurq.News.Security.Service.RolService;
import daiaurq.News.Service.PeriodistaService;
import daiaurq.News.dto.dtoPeriodista;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author daiau
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/periodista")
public class PeriodistaController {

    @Autowired
    PeriodistaService periodistaService;
    @Autowired
    RolService rolService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public ResponseEntity<List<Periodista>> listaPeriodistas() {
        List<Periodista> periodistas = periodistaService.listaPeriodistas();

        if (periodistas.isEmpty()) {
            return new ResponseEntity(new Mensaje("Lista de periodistas vacia"), HttpStatus.OK);
        }

        return new ResponseEntity(periodistas, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Periodista> create(@RequestBody dtoPeriodista dtoPerio, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }

        if (periodistaService.existsByNombreUsuario(dtoPerio.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Este nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);

        }

        if (periodistaService.existsByEmail(dtoPerio.getEmail())) {
            return new ResponseEntity(new Mensaje("Este email de periodista ya existe"), HttpStatus.BAD_REQUEST);
        }

        List<Noticia> noticias = new ArrayList<>();
        Date date = new Date();

        Periodista periodista = new Periodista(noticias, dtoPerio.getSueldo(), dtoPerio.getNombre(),dtoPerio.getNombreUsuario(), dtoPerio.getEmail(), passwordEncoder.encode(dtoPerio.getPassword()), dtoPerio.getFechaNacimiento());
        
        Set<Rol> roles = new HashSet<>();    
        
         if (dtoPerio.getRoles().contains("periodista")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_PERIODISTA));
        }        
        
        periodista.setFechaAlta(date);  
        periodista.setRoles(roles);
        periodistaService.savePeriodista(periodista);
        
        return new ResponseEntity( new Mensaje("Periodista creado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!periodistaService.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        
        periodistaService.deletePeriodista(id);
        
        return new ResponseEntity(new Mensaje("Periodista eliminado"), HttpStatus.OK);  
        
    }
}

