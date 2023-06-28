package com.Dochmart.app.rest.Controller;

import com.Dochmart.app.rest.Model.Usuario;
import com.Dochmart.app.rest.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/***
 * Controles que se pueden utilizar en esta API
 * POST   = Crear       = Create
 * GET    = Consulta    = Refresh
 * PUT    = Actualizar  = Update
 * DELETE = Eliminar    = Delete
 * * **/
@Controller
@RestController
@RequestMapping("app/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping(value = "/HolaPersona")
    public String Saludo(){
        return usuarioService.HelloWorld();
    }
    @GetMapping(value="/person")
    public List<Usuario> getPersona(){
        return usuarioService.getUser();
    }
    @GetMapping(value="/person/{id}")
    public Optional<Usuario> getPersona(@PathVariable long id){
        return usuarioService.getUserByID(id);
    }
    @PostMapping(value= "/saveperson")
    public String savePerson(@RequestBody Usuario usuario){
        return usuarioService.saveUser(usuario);
    }
    @PutMapping(value="/updatePerson/{id}")
    public String updatePerson(@PathVariable long id, @RequestBody Usuario usuario){
        return usuarioService.updateUser(id, usuario);
    }
    @DeleteMapping(value="deletePerson/{id}")
    public String deletePerson(@PathVariable long id){return usuarioService.deleteUser(id);}



}

