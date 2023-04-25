package daiaurq.News.dto;

import daiaurq.News.Entity.Noticia;
import java.util.List;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author daiau
 */
public class dtoCategoria {

    @NotBlank
    private String nombre;
    @NotBlank
    private List<Noticia> noticias;

    public dtoCategoria() {
    }

    public dtoCategoria(String nombre, List<Noticia> noticias) {
        this.nombre = nombre;
        this.noticias = noticias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

}
