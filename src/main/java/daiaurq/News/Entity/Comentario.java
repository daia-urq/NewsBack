package daiaurq.News.Entity;

import daiaurq.News.Security.Entity.Usuario;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_noticia", nullable = false)
    private Noticia noticia;    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;

    public Comentario() {
    }

    public Comentario(String comentario, Usuario usuario, Noticia noticia, Date fechaCreacion) {
        this.comentario = comentario;
        this.usuario = usuario;
        this.noticia = noticia;
        this.fechaCreacion = fechaCreacion;
    }    
}