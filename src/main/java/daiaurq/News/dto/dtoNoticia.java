package daiaurq.News.dto;

import daiaurq.News.Entity.Categoria;
import daiaurq.News.Entity.Periodista;
import java.util.ArrayList;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daiau
 */
@Getter
@Setter
public class dtoNoticia {

    @NotBlank
    private String titulo;
    @NotBlank
    private ArrayList<String> cuerpo;
    
    private Date fechaCreacion;
    @NotBlank
    private String imagen;
    @NotBlank
    private Categoria categoria;
    @NotBlank
    private int creador;

    public dtoNoticia() {
    }

    public dtoNoticia(String titulo,  ArrayList<String> cuerpo, Date fechaCreacion, String imagen, Categoria categoria, int creador) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fechaCreacion = fechaCreacion;
        this.imagen = imagen;
        this.categoria = categoria;
        this.creador = creador;
    }
}
