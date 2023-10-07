package com.tp.ap.servicios;

import com.tp.ap.entidades.Tecnico;
import com.tp.ap.repositorios.TecnicoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TecnicoServicio {

    @Autowired
    TecnicoRepositorio tecnicoRepositorio;

    public List<Tecnico> list() {
        return tecnicoRepositorio.findAll();
    }

    public Optional<Tecnico> getOne(int id) {
        return tecnicoRepositorio.findById(id);
    }

    public Optional<Tecnico> getByNombre(String nombre) {
        return tecnicoRepositorio.findByNombre(nombre);
    }

    public void save(Tecnico tecnico) {
        tecnicoRepositorio.save(tecnico);
    }

    public void delete(int id) {
        tecnicoRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return tecnicoRepositorio.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return tecnicoRepositorio.existsByNombre(nombre);
    }

}
