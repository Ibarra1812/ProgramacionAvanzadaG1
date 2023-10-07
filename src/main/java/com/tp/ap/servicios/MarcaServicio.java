package com.tp.ap.servicios;

import com.tp.ap.entidades.Marca;
import com.tp.ap.repositorios.MarcaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarcaServicio {

    @Autowired
    MarcaRepositorio marcaRepositorio;

    public List<Marca> list() {
        return marcaRepositorio.findAll();
    }

    public Optional<Marca> getOne(int id) {
        return marcaRepositorio.findById(id);
    }

    public Optional<Marca> getByNombre(String nombre) {
        return marcaRepositorio.findByNombre(nombre);
    }

    public void save(Marca marca) {
        marcaRepositorio.save(marca);
    }

    public void delete(int id) {
        marcaRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return marcaRepositorio.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return marcaRepositorio.existsByNombre(nombre);
    }

}
