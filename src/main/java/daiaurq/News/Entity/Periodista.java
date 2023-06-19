package daiaurq.News.Entity;

import daiaurq.News.Security.Entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
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
public class Periodista extends Usuario{

    @OneToMany
    private List<Noticia> noticias;
    private int sueldo;  

    public Periodista() {
    }

    public Periodista(List<Noticia> noticias, int sueldo, String nombre, String nombreUsuario, String email, String password, Date fechaNacimiento) {
        super(nombre, nombreUsuario, email, password, fechaNacimiento);
        this.noticias = noticias;
        this.sueldo = sueldo;
    }   
    
}