package daiaurq.News.Entity;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @Column(columnDefinition = "longblob")
    private ArrayList<String> cuerpo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    private String imagen;
    @ManyToOne
    private Categoria categoria;    
    @ManyToOne
    private Periodista periodista;

    public Noticia() {
    }

    public Noticia(String titulo, ArrayList<String> cuerpo, Date fechaCreacion, String imagen, Categoria categoria, Periodista periodista) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fechaCreacion = fechaCreacion;
        this.imagen = imagen;
        this.categoria = categoria;
        this.periodista = periodista;
    }

}
