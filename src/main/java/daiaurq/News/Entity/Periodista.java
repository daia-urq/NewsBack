package daiaurq.News.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import daiaurq.News.Security.Entity.Usuario;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daiau
 */
@Getter
@Setter
@Entity
@Table(name = "periodistas")
public class Periodista extends Usuario {

    private int sueldo;
    @OneToMany(mappedBy = "periodista", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Noticia> noticias;

    public Periodista() {
    }

    public Periodista(int sueldo, List<Noticia> noticias, String nombre, String apellido, String nombreUsuario, String email, String password, LocalDate fechaNacimiento) {
        super(nombre, apellido, nombreUsuario, email, password, fechaNacimiento);
        this.sueldo = sueldo;
        this.noticias = noticias;
    }
}
