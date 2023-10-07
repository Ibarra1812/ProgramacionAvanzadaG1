package com.tp.ap.servicios;

import com.tp.ap.entidades.Modelo;
import com.tp.ap.repositorios.ModeloRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModeloServicio {

    @Autowired
    ModeloRepositorio modeloRepositorio;

    public List<Modelo> list() {
        return modeloRepositorio.findAll();
    }

    public Optional<Modelo> getOne(int id) {
        return modeloRepositorio.findById(id);
    }

    public Optional<Modelo> getByNombre(String nombre) {
        return modeloRepositorio.findByNombre(nombre);
    }

    public void save(Modelo modelo) {
        modeloRepositorio.save(modelo);
    }

    public void delete(int id) {
        modeloRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return modeloRepositorio.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return modeloRepositorio.existsByNombre(nombre);
    }

}
