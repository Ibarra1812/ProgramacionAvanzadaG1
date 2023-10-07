package com.tp.ap.repositorios;

import com.tp.ap.entidades.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TecnicoRepositorio extends JpaRepository<Tecnico, Integer> {
    Optional<Tecnico> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
