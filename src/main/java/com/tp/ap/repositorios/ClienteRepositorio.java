package com.tp.ap.repositorios;

import com.tp.ap.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
