package com.tp.ap.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String patente;

    @ManyToOne(targetEntity = Marca.class)
    @JoinColumn(name = "Marca_id")
    private Marca marca;

    @ManyToOne(targetEntity = Modelo.class)
    @JoinColumn(name = "Modelo_id")
    private Modelo modelo;

    @ManyToOne(targetEntity = Tecnico.class)
    @JoinColumn(name = "Tecnico_id")
    private Tecnico tecnico;

    public Vehiculo() {
    }

    public Vehiculo(String patente, Marca marca, Modelo modelo, Tecnico tecnico) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.tecnico = tecnico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
}
