package com.Dochmart.app.rest.Controller;

import com.Dochmart.app.rest.Model.Cementera;
import com.Dochmart.app.rest.Model.Ubicacion;
import com.Dochmart.app.rest.Service.CementeraService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/***
 * Controles que se pueden utilizar en esta API
 * POST = Crear      = Create
 * GET  = Consulta   = Refresh
 * PUT  = Actualizar = Update
 * DELETE= Eliminar  = Delete
 *
 * * **/

@Controller
@RestController
@RequestMapping("app/Cementeras")
@AllArgsConstructor
public class CementeraController {

    private final CementeraService cementeraService;

    @GetMapping(value = "/HolaCementera")
    public String Hello(){
        return cementeraService.HelloWorld() ;
    }

    @GetMapping(value= "/Cmentera")
    public List<Cementera> getallCementeras(){
        return cementeraService.getCementera() ;
    }
    @GetMapping(value="/Cementera/{id}")
    public Optional<Cementera> getCementera(@PathVariable long id){
        return cementeraService.getCementeraByID(id);
    }

    @PostMapping(value="/saveCementera")
    public String saveCementera(@RequestBody Cementera cementera){
        return cementeraService.saveCementera(cementera);
    }

    @PutMapping(value="/update/{id}")
    public String updateCementera(@PathVariable long id, @RequestBody Cementera cementera){
        return cementeraService.updateCementera(id, cementera);
    }

    @DeleteMapping(value="delete/{id}")
    public String deleteCementera(@PathVariable long id){
        return cementeraService.deleteCementera(id);
    }

    @PostMapping(value="/validarUbicacion")
    public List<Cementera> validarUbicacion(@RequestBody Ubicacion ubicacion) throws Exception {
        return cementeraService.validarUbicacion(ubicacion);
    }

}
