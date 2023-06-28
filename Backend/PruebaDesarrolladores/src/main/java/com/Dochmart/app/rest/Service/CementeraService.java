package com.Dochmart.app.rest.Service;

import com.Dochmart.app.rest.Model.Cementera;
import com.Dochmart.app.rest.Model.Ubicacion;
import com.Dochmart.app.rest.Repository.CementeraRepository;
import com.Dochmart.app.rest.Repository.UbicacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.Dochmart.app.rest.utils.distancesUtil.calcularDistancia;

@Service
@AllArgsConstructor
public class CementeraService {

    private final CementeraRepository cementeraRepository;

    private final UbicacionRepository ubicacionRepository;

    public String HelloWorld(){
        return "HOLA MUNDO!!!";
    }

    public List<Cementera> getCementera(){
        return cementeraRepository.findAll();
    }
    public Optional<Cementera> getCementeraByID(@PathVariable long id){
        return cementeraRepository.findById(id);
    }

    public String saveCementera(@RequestBody Cementera cementera){
        try {
            Ubicacion newUbicacion = ubicacionRepository.save(cementera.getUbicacion());
            cementera.setUbicacion(newUbicacion);
            cementeraRepository.save(cementera);
            return "Saved Cementera";
        } catch (Exception e) {
            System.out.println(e);
            return "Error";
        }
    }

    public List<Cementera> validarUbicacion(Ubicacion ubicacionCliente) throws Exception{
        try {
            List<Cementera> resultado = new ArrayList<>();
            List<Cementera> cementeras = cementeraRepository.findAll();
            for(Cementera cementera : cementeras){
                double distancia = calcularDistancia(
                        ubicacionCliente.getLatitud(),
                        ubicacionCliente.getLongitud(),
                        cementera.getUbicacion().getLatitud(),
                        cementera.getUbicacion().getLongitud()
                );
                System.out.println(cementera.getNombre() + " Distancia: " + distancia + " radio: " + cementera.getRadio());
                if (distancia < cementera.getRadio()){
                    resultado.add(cementera);
                }
            }
            return resultado;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public String updateCementera(@PathVariable long id, @RequestBody Cementera cementera){
        Cementera updatedCementera = cementeraRepository.findById(id).get();
        updatedCementera.setNombre(cementera.getNombre());
        updatedCementera.setRadio(cementera.getRadio());
        updatedCementera.setUbicacion(cementera.getUbicacion());
        cementeraRepository.save(updatedCementera);
        return "Updated Cementera";
    }

    public String deleteCementera(@PathVariable long id){
        Cementera deletedCementera = cementeraRepository.findById(id).get();
        cementeraRepository.delete(deletedCementera);
        return "Deleted Cementera";
    }


}
