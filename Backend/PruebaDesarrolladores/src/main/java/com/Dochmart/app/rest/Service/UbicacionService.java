package com.Dochmart.app.rest.Service;
import com.Dochmart.app.rest.Model.Ubicacion;
import com.Dochmart.app.rest.Repository.UbicacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UbicacionService {
    private final UbicacionRepository ubicacionRepository;

    public String HelloWorld(){
        return "Funcionando el modulo de las Ubicaciones!!!";
    }

    public List<Ubicacion> getUbi(){
        return ubicacionRepository.findAll();
    }
    public Optional<Ubicacion> getUbiByID(@PathVariable long id){
        return ubicacionRepository.findById(id);
    }

    public String saveUbi(Ubicacion ubicacion){
        ubicacionRepository.save(ubicacion);
        return "Saved Ubi";
    }

    public String updateUbi(@PathVariable long id, @RequestBody Ubicacion ubicacion){
        Ubicacion UpdatedUbicacion = ubicacionRepository.findById(id).get();
        UpdatedUbicacion.setLatitud(ubicacion.getLatitud());
        UpdatedUbicacion.setLongitud(ubicacion.getLatitud());
        ubicacionRepository.save(UpdatedUbicacion);
        return "Updated Ubi";
    }

    public String deleteUbi(@PathVariable long id){
        Ubicacion deletedUbi = ubicacionRepository.findById(id).get();
        ubicacionRepository.delete(deletedUbi);
        return "Deleted Ubi";
    }
}
