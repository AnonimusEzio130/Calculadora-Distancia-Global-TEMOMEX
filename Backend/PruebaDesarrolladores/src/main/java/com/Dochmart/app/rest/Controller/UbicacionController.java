package com.Dochmart.app.rest.Controller;

import com.Dochmart.app.rest.Model.Ubicacion;
import com.Dochmart.app.rest.Service.UbicacionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("app/ubicacion")
@AllArgsConstructor
public class UbicacionController {
    private final UbicacionService ubicacionService;

    @GetMapping(value = "/HolaUbi")
    public String Saludo(){
        return ubicacionService.HelloWorld();
    }
    @GetMapping(value="/ubicaciones")
    public List<Ubicacion> getUbicacion(){
        return ubicacionService.getUbi();
    }
    @GetMapping(value="/ubicacion/{id}")
    public Optional<Ubicacion> getUbicacion(@PathVariable long id){
        return ubicacionService.getUbiByID(id);
    }
    @PostMapping(value= "/saveubicacion")
    public String saveUbicacion(@RequestBody Ubicacion ubicacion){
        return ubicacionService.saveUbi(ubicacion);
    }
    @PutMapping(value="/updateUbicion/{id}")
    public String updateUbicacion(@PathVariable long id, @RequestBody Ubicacion ubicacion){
        return ubicacionService.updateUbi(id, ubicacion);
    }
    @DeleteMapping(value="deleteUbicacion/{id}")
    public String deleteUbicacion(@PathVariable long id){
        return ubicacionService.deleteUbi(id);
    }

}
