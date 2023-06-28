package com.Dochmart.app.rest.Model;
import javax.persistence.*;

@Entity
@Table(schema = "UbicationCrud")
public class Ubicacion {
    /***
     * Many to ONE es la relacion entre entidades de muchas a una, siendo que:
     * Many corresponde a la clase que se ejecuta y to ONE corresponde al atributo que
     * es otra entidad, en este caso: MAny to One, corresponde a que muchas tareas pueden estar
     * asignadas a una sola persona. esto de pendera del modelo de negocio del cliente
     * en simples palabras, se puede tener muchas tareas que le pertenecen a una
     * persona
     * en el caso de One to Many se utiliza una Lista para los datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private double Latitud;

    @Column
    private double Longitud;

    // getters and setters
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public double getLatitud(){return Latitud;}
    public void setLatitud(double latitud){this.Latitud = latitud;}
    public double getLongitud(){
        return Longitud;
    }
    public void setLongitud(double longitud){
        this.Longitud = longitud;
    }

}