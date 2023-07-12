
package daiaurq.News.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daiau
 */
@Getter
@Setter
public class dtoComentario {
    
    @NotBlank
    private String comentario;
    @NotBlank
    private int usuario;
    @NotBlank 
    private int noticia;        

    public dtoComentario() {
    }

    public dtoComentario(String comentario, int usuario, int noticia) {
        this.comentario = comentario;
        this.usuario = usuario;
        this.noticia = noticia;
    }    
}
