package daiaurq.News.Security.Controller;

import daiaurq.News.Entity.Mensaje;
import daiaurq.News.Security.Dto.JwtDto;
import daiaurq.News.Security.Dto.LoginUsuario;
import daiaurq.News.Security.Dto.NuevoUsuario;
import daiaurq.News.Security.Entity.Rol;
import daiaurq.News.Security.Entity.Usuario;
import daiaurq.News.Security.Enums.RolNombre;
import daiaurq.News.Security.Service.RolService;
import daiaurq.News.Security.Service.UsuarioService;
import daiaurq.News.Security.jwt.JwtProvider;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("auth")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Este nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("Este email de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        Date date = new Date();
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getFechaNacimiento());

        Set<Rol> roles = new HashSet<>();

        if (nuevoUsuario.getRoles().contains("user")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER));
        }

        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN));
        }

        usuario.setFechaAlta(date);
        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Mensaje> logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, null, authentication);
        }

        Mensaje mensaje = new Mensaje("Logout exitoso");
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        usuarioService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> upDate(@PathVariable("id") int id, @RequestBody dtoUsuario dtoUsu) {
//        if (!usuarioService.existsById(id)) {
//            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
//        }
//
//        if (usuarioService.existsByNombreUsuario(dtoUsu.getNombreUsuario()) && usuarioService.getByNombreUsuario(dtoUsu.getNombreUsuario()).get().getId() != id) {
//            return new ResponseEntity(new Mensaje("Este nombre de usuario ya existe"), HttpStatus.BAD_GATEWAY);
//        }
//
//        if (StringUtils.isBlank(dtoUsu.getPassword())) {
//            return new ResponseEntity(new Mensaje("Ingrese un nuevo password"), HttpStatus.BAD_REQUEST);
//        }
//        //probar si los password son iguales
//
//        Usuario usuario = usuarioService.getOne(id);
//
//        usuario.setNombreUsuario(dtoUsu.getNombreUsuario());
//        usuario.setPassword(dtoUsu.getPassword());
//
//        usuarioService.save(usuario);
//
//        return new ResponseEntity(new Mensaje("usuario modificado"), HttpStatus.OK);
//    }

    @GetMapping("/getOne/{nombreUsuario}")
    public ResponseEntity<?> getOne(@PathVariable("nombreUsuario")String nombreUsuario){
        
        if(nombreUsuario.isEmpty()){
            return new ResponseEntity(new Mensaje("Debe enviar un ombre de usuario"), HttpStatus.BAD_REQUEST);
        }
        
        Optional<Usuario> usuario = usuarioService.getByNombreUsuario(nombreUsuario);
        
        if(usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
        
        return new ResponseEntity(new Mensaje("No se encuantra el usuario"), HttpStatus.BAD_REQUEST);
    }
    
    }
    

