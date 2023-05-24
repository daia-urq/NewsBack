package daiaurq.News.Controller;


import org.apache.commons.lang3.StringUtils;
import daiaurq.News.Entity.Mensaje;
import daiaurq.News.Entity.Noticia;
import daiaurq.News.Service.CategoriaService;
import daiaurq.News.Service.NoticiaService;
import daiaurq.News.dto.dtoNoticia;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/noticia")
public class NoticiaController {

    @Autowired
    NoticiaService noticiaService;
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/list")
    public ResponseEntity<List<Noticia>> listaNoticia() {
        List<Noticia> listaNoticia = noticiaService.listaNoticias();

        if (listaNoticia.isEmpty()) {
            return new ResponseEntity(new Mensaje("error"), HttpStatus.OK);
        }
        return new ResponseEntity(listaNoticia, HttpStatus.OK);
    }

    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoNoticia dtoNoti) throws Exception {
        if (StringUtils.isBlank(dtoNoti.getTitulo())) {
            return new ResponseEntity(new Mensaje("Debe ingresar un nombre para la noticia"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoNoti.getCuerpo())) {
            return new ResponseEntity(new Mensaje("Debe ingresar un cuerpo para la noticia"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoNoti.getImagen())) {
            return new ResponseEntity(new Mensaje("Debe ingresar una imagen para la noticia"), HttpStatus.BAD_REQUEST);
        }

        if (noticiaService.existsByTitulo(dtoNoti.getTitulo())) {
            return new ResponseEntity(new Mensaje("Ya existe esta noticia"), HttpStatus.BAD_REQUEST);
        }
    
        if (!categoriaService.existsById(dtoNoti.getCategoria().getId())) {
            return new ResponseEntity(new Mensaje("No existe esta categoria"), HttpStatus.BAD_REQUEST);
        }

        if (!categoriaService.existsByNombre(dtoNoti.getCategoria().getNombre())) {
            return new ResponseEntity(new Mensaje("No existe nombre de categoria"), HttpStatus.BAD_REQUEST);
        }

        Date fecha;
        fecha = new Date();

        Noticia noticia = new Noticia(dtoNoti.getTitulo(), dtoNoti.getCuerpo(), fecha, dtoNoti.getImagen(), dtoNoti.getCategoria());
        noticiaService.saveNoticia(noticia);

        return new ResponseEntity(new Mensaje("Noticia creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> upDate(@PathVariable("id") int id, @RequestBody dtoNoticia dtoNoti) throws Exception {
        if (!noticiaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoNoti.getTitulo())) {
            return new ResponseEntity(new Mensaje("Ingrese un titulo"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoNoti.getCuerpo())) {
            return new ResponseEntity(new Mensaje("Ingrese un cuerpo de noticia"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoNoti.getImagen())) {
            return new ResponseEntity(new Mensaje("Ingrese una imagen para la noticia"), HttpStatus.BAD_REQUEST);
        }
        
        
        Optional<Noticia> noti = noticiaService.getByTitulo(dtoNoti.getTitulo());
        
        //agregar validacion de si existe una noticia con ese titulo 
        if (noticiaService.existsByTitulo(dtoNoti.getTitulo()) && noti.get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe esta noticia"), HttpStatus.BAD_REQUEST);
        }
        
        if (!categoriaService.existsById(dtoNoti.getCategoria().getId())) {
            return new ResponseEntity(new Mensaje("No existe esta categoria"), HttpStatus.BAD_REQUEST);
        }

        if (!categoriaService.existsByNombre(dtoNoti.getCategoria().getNombre())) {
            return new ResponseEntity(new Mensaje("No existe nombre de categoria"), HttpStatus.BAD_REQUEST);
        }

        Noticia noticia = noticiaService.getOne(id).get();
        noticia.setTitulo(dtoNoti.getTitulo());
        noticia.setCuerpo(dtoNoti.getCuerpo());
        noticia.setImagen(dtoNoti.getImagen());
        noticia.setCategoria(dtoNoti.getCategoria());

        noticiaService.saveNoticia(noticia);

        return new ResponseEntity(new Mensaje("Noticia modificada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id
    ) {
        if (!noticiaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        noticiaService.deleteNoticia(id);
        return new ResponseEntity(new Mensaje("Noticia eliminada"), HttpStatus.OK);
    }
}
