package daiaurq.News.Entity;

import daiaurq.News.Security.Entity.Usuario;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daiau
 */
@Getter
@Setter
public class Periodista extends Usuario{
    
    private ArrayList<Noticia> cantidadNoticias;
    private int sueldo;     

    public Periodista(int sueldo, String nombre, String nombreUsuario, String email, String password) {
        super(nombre, nombreUsuario, email, password);
        this.sueldo = sueldo;
    }
      
    
}