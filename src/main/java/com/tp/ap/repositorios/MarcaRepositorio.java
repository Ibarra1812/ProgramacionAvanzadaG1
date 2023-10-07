package com.tp.ap.repositorios;

import com.tp.ap.entidades.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MarcaRepositorio extends JpaRepository<Marca, Integer> {
    Optional<Marca> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
