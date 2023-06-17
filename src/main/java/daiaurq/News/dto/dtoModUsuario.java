package daiaurq.News.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daiau
 */
@Getter
@Setter
public class dtoModUsuario {    
    
    private String nombre;
    
    private String nombreUsuario;   
    
    private String password;
    
    private Date fechaNacimiento;
   
}
