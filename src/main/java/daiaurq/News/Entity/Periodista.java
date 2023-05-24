package daiaurq.News.Entity;

import daiaurq.News.Security.Entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
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

    public Periodista(ArrayList<Noticia> cantidadNoticias, int sueldo) {
        this.cantidadNoticias = cantidadNoticias;
        this.sueldo = sueldo;
    }

    public Periodista(ArrayList<Noticia> cantidadNoticias, int sueldo, String nombre, String nombreUsuario, String email, String password, Date fechaNacimiento) {
        super(nombre, nombreUsuario, email, password, fechaNacimiento);
        this.cantidadNoticias = cantidadNoticias;
        this.sueldo = sueldo;
    }

   
      
    
}