
package daiaurq.News.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    @JsonIgnore 
    List<Noticia> noticias;

    public Categoria() {
    }

    public Categoria(String nombre, List<Noticia> noticias) {
        this.nombre = nombre;
        this.noticias = noticias;
    }
    
    
    
}
