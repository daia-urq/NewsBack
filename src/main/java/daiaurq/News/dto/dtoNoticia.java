package daiaurq.News.dto;

import daiaurq.News.Entity.Categoria;
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
    private String cuerpo;
    private Date fechaCreacion;
    @NotBlank
    private String imagen;
    @NotBlank
    private Categoria categoria;

    public dtoNoticia() {
    }

    public dtoNoticia(String titulo, String cuerpo, Date fechaCreacion, String imagen, Categoria categoria) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fechaCreacion = fechaCreacion;
        this.imagen = imagen;
        this.categoria = categoria;
    }
}
