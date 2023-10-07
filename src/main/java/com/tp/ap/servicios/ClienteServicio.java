package com.tp.ap.servicios;

import com.tp.ap.entidades.Cliente;
import com.tp.ap.repositorios.ClienteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    public List<Cliente> list() {
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> getOne(int id) {
        return clienteRepositorio.findById(id);
    }

    public Optional<Cliente> getByNombre(String nombre) {
        return clienteRepositorio.findByNombre(nombre);
    }

    public void save(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    public void delete(int id) {
        clienteRepositorio.deleteById(id);
    }

    public boolean existsById(int id) {
        return clienteRepositorio.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return clienteRepositorio.existsByNombre(nombre);
    }

}
