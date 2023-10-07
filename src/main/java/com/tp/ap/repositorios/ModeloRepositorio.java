package com.tp.ap.repositorios;

import com.tp.ap.entidades.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ModeloRepositorio extends JpaRepository<Modelo, Integer> {
    Optional<Modelo> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
