package daiaurq.News.Security.Dto;

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
public class NuevoUsuario {
    
    @NotBlank
    private String nombre;
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private Date fechaNacimiento;
   
    private String roles ;

}    

