package com.Dochmart.app.rest.Service;

import com.Dochmart.app.rest.Model.Ubicacion;
import com.Dochmart.app.rest.Model.Usuario;
import com.Dochmart.app.rest.Repository.UbicacionRepository;
import com.Dochmart.app.rest.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UbicacionRepository ubicacionRepository;

    public String HelloWorld(){
        return "Funcionando el modulo de Usuarios!!!";
    }

    public List<Usuario> getUser(){
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> getUserByID(@PathVariable long id){
        return usuarioRepository.findById(id);
    }

    public String saveUser(@RequestBody Usuario usuario){
        try {
            Ubicacion newUbicacion = ubicacionRepository.save(usuario.getUbicacion());
            usuario.setUbicacion(newUbicacion);
            usuarioRepository.save(usuario);
            return "Saved person";
        } catch (Exception e) {
            System.out.println(e);
            return "Error";
        }

    }

    public String updateUser(@PathVariable long id, @RequestBody Usuario usuario){
        Usuario UpdatedUser = usuarioRepository.findById(id).get();
        UpdatedUser.setNombre(usuario.getNombre());
        UpdatedUser.setUbicacion(usuario.getUbicacion());
        usuarioRepository.save(UpdatedUser);
        return "Updated User";
    }

    public String deleteUser(@PathVariable long id){
        Usuario deletedUser = usuarioRepository.findById(id).get();
        usuarioRepository.delete(deletedUser);
        return "Deleted User";
    }
}
