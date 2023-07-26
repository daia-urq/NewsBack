package daiaurq.News.Controller;

import daiaurq.News.Entity.Mensaje;
import daiaurq.News.Entity.Periodista;
import daiaurq.News.Security.Entity.Rol;
import daiaurq.News.Security.Enums.RolNombre;
import daiaurq.News.Security.Service.RolService;
import daiaurq.News.Service.PeriodistaService;
import daiaurq.News.dto.dtoPeriodista;
import daiaurq.News.dto.dtoPeriodistaEdit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author daiau
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/periodista")
public class PeriodistaController {

    @Autowired
    PeriodistaService periodistaService;
    @Autowired
    RolService rolService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public ResponseEntity<List<Periodista>> listaPeriodistas() {
        List<Periodista> periodistas = periodistaService.listaPeriodistas();

        if (periodistas.isEmpty()) {
            return new ResponseEntity(new Mensaje("Lista de periodistas vacia"), HttpStatus.OK);
        }

        return new ResponseEntity(periodistas, HttpStatus.OK);
    }

    @GetMapping("/getOne/{nombreUsuario}")
    public ResponseEntity<Periodista> getOne(@PathVariable("nombreUsuario") String nombreUsuario) {

        Optional<Periodista> periodista = periodistaService.getByNombreUsuario(nombreUsuario);

        if (periodista.isEmpty()) {
            return new ResponseEntity(new Mensaje("No existe este nombre de usuario"), HttpStatus.OK);
        }

        return new ResponseEntity(periodista, HttpStatus.OK);
    }

    @GetMapping("/getOneid/{id}")
    public ResponseEntity<Periodista> getOneID(@PathVariable("id") int id) {
        if (id <= 0) {
            return new ResponseEntity(new Mensaje("El id debe ser un numero válido"), HttpStatus.BAD_REQUEST);
        }
        Periodista periodista = periodistaService.findById(id);

        if (periodista == null) {
            return new ResponseEntity(new Mensaje("No existe un periodista con este Id"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(periodista, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Periodista> create(@RequestBody dtoPeriodista dtoPerio, BindingResult bindingResult) {

//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
//        }
        if (StringUtils.isBlank(dtoPerio.getNombre())) {
            return new ResponseEntity(new Mensaje("Ingrese un nombre"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoPerio.getApellido())) {
            return new ResponseEntity(new Mensaje("Ingrese un apellido"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoPerio.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Ingrese un nombre de usuario"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoPerio.getEmail())) {
            return new ResponseEntity(new Mensaje("Ingrese un email"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoPerio.getPassword())) {
            return new ResponseEntity(new Mensaje("Ingrese un password"), HttpStatus.BAD_REQUEST);
        }

        if (dtoPerio.getFechaNacimiento() == null) {
            return new ResponseEntity(new Mensaje("Ingrese una fecha de nacimiento"), HttpStatus.BAD_REQUEST);
        }

        if (periodistaService.existsByNombreUsuario(dtoPerio.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Este nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (periodistaService.existsByEmail(dtoPerio.getEmail())) {
            return new ResponseEntity(new Mensaje("Este email de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (periodistaService.existsByNombreUsuario(dtoPerio.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Este nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (periodistaService.existsByEmail(dtoPerio.getEmail())) {
            return new ResponseEntity(new Mensaje("Este email de periodista ya existe"), HttpStatus.BAD_REQUEST);
        }

        List array = new ArrayList();
        Date date = new Date();

        Periodista periodista = new Periodista(dtoPerio.getSueldo(), array, dtoPerio.getNombre(), dtoPerio.getApellido(), dtoPerio.getNombreUsuario(), dtoPerio.getEmail(), passwordEncoder.encode(dtoPerio.getPassword()), dtoPerio.getFechaNacimiento());

        Set<Rol> roles = new HashSet<>();

        if ("periodista".equals(dtoPerio.getRoles())) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_PERIODISTA));
        }

        periodista.setFechaAlta(date);
        periodista.setRoles(roles);
        periodistaService.savePeriodista(periodista);

        return new ResponseEntity(new Mensaje("Periodista creado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!periodistaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        // Validar si el periodista tiene noticias asociadas
        List<Integer> periodistasSinNoticias = periodistaService.findIdPeriodistaSinNoticia();

        if (!periodistasSinNoticias.contains(id)) {
            return new ResponseEntity(new Mensaje("Este periodista tiene noticias asociadas"), HttpStatus.BAD_REQUEST);
        }

        periodistaService.deletePeriodista(id);

        return new ResponseEntity(new Mensaje("Periodista eliminado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Periodista> upDate(@PathVariable("id") int id, @RequestBody dtoPeriodistaEdit dtoPerio) {

        if (!periodistaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (periodistaService.existsByNombreUsuario(dtoPerio.getNombreUsuario()) && periodistaService.getByNombreUsuario(dtoPerio.getNombreUsuario()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Este nombre de usuario ya existe"), HttpStatus.BAD_GATEWAY);
        }

        if (periodistaService.existsByEmail(dtoPerio.getEmail()) && periodistaService.getByEmail(dtoPerio.getEmail()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Este email ya existe"), HttpStatus.BAD_GATEWAY);
        }

        if (StringUtils.isBlank(dtoPerio.getNombre())) {
            return new ResponseEntity(new Mensaje("Ingrese un nombre"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoPerio.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Ingrese un nombre de usuario de periodista"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoPerio.getApellido())) {
            return new ResponseEntity(new Mensaje("Ingrese un apellido de periodista"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoPerio.getEmail())) {
            return new ResponseEntity(new Mensaje("Ingrese un mail"), HttpStatus.BAD_REQUEST);
        }

        if (dtoPerio.getFechaNacimiento() == null) {
            return new ResponseEntity(new Mensaje("Ingrese una fecha de nacimiento"), HttpStatus.BAD_REQUEST);
        }

        if (dtoPerio.getSueldo() == 0) {
            return new ResponseEntity(new Mensaje("Ingrese un sueldo valido"), HttpStatus.BAD_REQUEST);
        }

        Periodista periodista = periodistaService.findById(id);

        if (dtoPerio.getPassword() != null) {
            if (!dtoPerio.getPassword().isEmpty()) {
                periodista.setPassword(passwordEncoder.encode(dtoPerio.getPassword()));
            } else {
                return new ResponseEntity(new Mensaje("Ingrese una contraseña"), HttpStatus.BAD_REQUEST);
            }
        }

        periodista.setNombre(dtoPerio.getNombre());
        periodista.setApellido(dtoPerio.getApellido());
        periodista.setNombreUsuario(dtoPerio.getNombreUsuario());
        periodista.setEmail(dtoPerio.getEmail());
        periodista.setFechaNacimiento(dtoPerio.getFechaNacimiento());
        periodista.setSueldo(dtoPerio.getSueldo());
        periodistaService.savePeriodista(periodista);

        return new ResponseEntity(new Mensaje("Periodista modificado"), HttpStatus.OK);
    }

}
