package daiaurq.News.Security.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    
    @Column(unique = true)
    private String nombreUsuario;
    
    private String email;
    
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles  = new HashSet<>();   
   
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;        
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAlta;

    public Usuario() {
    }

    public Usuario(String nombre, String nombreUsuario, String email, String password, Date fechaNacimiento) {        
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;     
    }
}
