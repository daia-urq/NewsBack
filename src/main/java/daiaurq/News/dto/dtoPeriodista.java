package daiaurq.News.dto;

import daiaurq.News.Entity.Noticia;
import daiaurq.News.Security.Dto.NuevoUsuario;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daiau
 */
@Getter
@Setter
public class dtoPeriodista extends NuevoUsuario {
       
    @NotBlank
    private int sueldo;  
    
    List<Noticia> noticias;
}
