package com.tp.ap.servicios;

import com.tp.ap.entidades.Vehiculo;
import com.tp.ap.repositorios.VehiculoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehiculoServicio {

    @Autowired
    VehiculoRepositorio vehiculoRepositorio;

    public List<Vehiculo> list() {
        return vehiculoRepositorio.findAll();
    }

    public Optional<Vehiculo> getOne(int id) {
        return vehiculoRepositorio.findById(id);
    }

    public Optional<Vehiculo> getByPatente(String patente) {
        return vehiculoRepositorio.findByPatente(patente);
    }

    public void save(Vehiculo vehiculo) {
        vehiculoRepositorio.save(vehiculo);
    }

    public void delete(int id) {
        vehiculoRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return vehiculoRepositorio.existsById(id);
    }

    public boolean existsByPatente(String patente) {
        return vehiculoRepositorio.existsByPatente(patente);
    }

}