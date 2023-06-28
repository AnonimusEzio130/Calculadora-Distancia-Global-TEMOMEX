package com.Dochmart.app.rest.Model;
import javax.persistence.*;

@Entity
@Table(schema = "User")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String Nombre;
    @ManyToOne
    @JoinColumn(name="Ubicacion")
    Ubicacion ubicacion;

    // getters and setters
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getNombre(){
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }


}