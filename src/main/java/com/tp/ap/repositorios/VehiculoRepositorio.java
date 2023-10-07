package com.tp.ap.repositorios;

import com.tp.ap.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer> {
    Optional<Vehiculo> findByPatente(String patente);
    boolean existsByPatente(String patente);
}
