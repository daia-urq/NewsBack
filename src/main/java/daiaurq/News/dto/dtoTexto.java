
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
public class dtoTexto {
    @NotBlank
    private String texto;

    public dtoTexto() {
    }

    public dtoTexto(String texto) {
        this.texto = texto;
    }
}
