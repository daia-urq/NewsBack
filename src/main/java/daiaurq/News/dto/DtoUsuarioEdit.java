package daiaurq.News.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daiau
 */
@Getter
@Setter
public class DtoUsuarioEdit {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String email;
    
    private String password;
    @NotNull
    private LocalDate fechaNacimiento;
}    

