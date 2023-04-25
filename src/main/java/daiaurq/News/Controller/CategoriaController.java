package daiaurq.News.Controller;

import daiaurq.News.Entity.Categoria;
import daiaurq.News.Entity.Mensaje;
import daiaurq.News.Entity.Noticia;
import daiaurq.News.Service.CategoriaService;
import daiaurq.News.dto.dtoCategoria;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/list")
    public ResponseEntity<List<Categoria>> listaCategoria() {
        List<Categoria> listaCategoria = categoriaService.listaCategorias();
        return new ResponseEntity(listaCategoria, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoCategoria dtoCat) throws Exception {
        if (StringUtils.isBlank(dtoCat.getNombre())) {
            return new ResponseEntity(new Mensaje("Debe ingresar un nombre para la categoría"), HttpStatus.BAD_REQUEST);
        }
        if (categoriaService.existsByNombre(dtoCat.getNombre())) {
            return new ResponseEntity(new Mensaje("Ya existe esta categoría"), HttpStatus.BAD_REQUEST);
        }
        List<Noticia> noticias = new ArrayList<>();

        Categoria categoria = new Categoria(dtoCat.getNombre(), noticias);

        categoriaService.saveCategoria(categoria);

        return new ResponseEntity(new Mensaje("Categoría creada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!categoriaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        List<Categoria> categoriasVacias = categoriaService.listaCategoriasVacias();
        Categoria categoria = categoriaService.getOne(id);

        if (!categoriasVacias.contains(categoria)) {
            return new ResponseEntity(new Mensaje("Esta categoria tiene noticias asociadas"), HttpStatus.OK);
        }

        categoriaService.deleteNoticia(id);
        return new ResponseEntity(new Mensaje("Categoria eliminada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> upDate(@PathVariable("id") int id, @RequestBody dtoCategoria dtoCat) throws Exception {
        if (!categoriaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoCat.getNombre())) {
            return new ResponseEntity(new Mensaje("Debe ingresar un nombre para la categoría"), HttpStatus.BAD_REQUEST);
        }

        if (categoriaService.existsByNombre(dtoCat.getNombre())) {
            return new ResponseEntity(new Mensaje("Ya existe este nombre de categoria"), HttpStatus.BAD_REQUEST);
        }

       Categoria categoria = categoriaService.getOne(id);
       categoria.setNombre(dtoCat.getNombre());
       
       categoriaService.saveCategoria(categoria);

        return new ResponseEntity(new Mensaje("Categoria modificada"), HttpStatus.OK);
    }
}
