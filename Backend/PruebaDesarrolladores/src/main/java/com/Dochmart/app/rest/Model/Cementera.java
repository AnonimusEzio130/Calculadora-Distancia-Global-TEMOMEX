package com.Dochmart.app.rest.Model;
import javax.persistence.*;

@Entity
@Table(schema = "CementeraCrud")
public class Cementera {
    /***
     * Many to ONE es la relacion entre entidades de muchas a una, siendo que:
     * Many corresponde a la clase que se ejecuta y to ONE corresponde al atributo que
     * es otra entidad, en este caso: MAny to One, corresponde a que muchas tareas pueden estar
     * asignadas a una sola persona. esto de pendera del modelo de negocio del cliente
     * en simples palabras, se puede tener muchas tareas que le pertenecen a una
     * persona
     *
     * en el caso de One to Many se utiliza una Lista para los datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name="Name")
    private String Nombre;
    @Column(name="Radius")
    private long Radio;
    @ManyToOne
    @JoinColumn(name="Location")
    Ubicacion ubicacion;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getNombre(){
        return Nombre;
    }
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public long getRadio(){return Radio;}
    public void setRadio(long Radio) {this.Radio = Radio;}

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}