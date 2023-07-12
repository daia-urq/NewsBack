package daiaurq.News.Controller;

import daiaurq.News.Entity.Comentario;
import daiaurq.News.Entity.Mensaje;
import daiaurq.News.Entity.Noticia;
import daiaurq.News.Security.Entity.Usuario;
import daiaurq.News.Security.Service.UsuarioService;
import daiaurq.News.Service.ComentarioService;
import daiaurq.News.Service.NoticiaService;
import daiaurq.News.dto.dtoComentario;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    NoticiaService noticiaService;

    @GetMapping("/noticia/{id}")
    public ResponseEntity<List<Comentario>> comentarioPorNoticia(@PathVariable("id") int id) {

        if (!comentarioService.existsByNoticiaId(id)) {
            return new ResponseEntity(new Mensaje("no existen comentarios para esta noticia"), HttpStatus.BAD_REQUEST);
        }
        List<Comentario> comentarios = comentarioService.listaComentarioPorNoticia(id);
        return new ResponseEntity(comentarios, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoComentario dtoComen) throws Exception {

        if (StringUtils.isBlank(dtoComen.getComentario())) {
            return new ResponseEntity(new Mensaje("Debe ingresar un comentario para la noticia"), HttpStatus.BAD_REQUEST);
        }

        if (dtoComen.getNoticia() < 0) {
            return new ResponseEntity(new Mensaje("El id del noticia debe ser un numero valido"), HttpStatus.BAD_REQUEST);
        }

        if (dtoComen.getUsuario() < 0) {
            return new ResponseEntity(new Mensaje("El id del usuario debe ser un numero valido"), HttpStatus.BAD_REQUEST);
        }

        if (!noticiaService.existsById(dtoComen.getNoticia())) {
            return new ResponseEntity(new Mensaje("Debe ingresar una noticia valida"), HttpStatus.BAD_REQUEST);
        }

        if (!usuarioService.existsById(dtoComen.getUsuario())) {
            return new ResponseEntity(new Mensaje("Debe ingresar un usuaio valido"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = usuarioService.getOne(dtoComen.getUsuario());
        Noticia noticia = noticiaService.getOne(dtoComen.getNoticia()).get();

        Date fecha;
        fecha = new Date();

        Comentario comentario = new Comentario(dtoComen.getComentario(), usuario, noticia, fecha);
        comentarioService.saveComentario(comentario);

        return new ResponseEntity(new Mensaje("Comentario creado"), HttpStatus.OK);
    }
}
