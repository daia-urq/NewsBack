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
public class dtoPeriodistaEdit extends DtoUsuarioEdit{
    
    @NotBlank
    private int sueldo;  
    
}
