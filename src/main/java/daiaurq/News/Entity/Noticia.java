package daiaurq.News.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daiau
 */
@Getter
@Setter
@Entity
@Table(name = "noticias")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String titulo;
    private String cuerpo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    private String imagen;

    public Noticia() {
    }

    public Noticia(String titulo, String cuerpo, Date fechaCreacion, String imagen) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fechaCreacion = fechaCreacion;
        this.imagen = imagen;
    }
  
}