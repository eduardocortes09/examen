package com.calificaciones.repository;


import com.calificaciones.entity.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpcionRepository extends JpaRepository<Opcion, Long> {
    Optional<Opcion> findByIdAndPreguntaId(Long idOpcion, Integer idPregunta);

    List<Opcion> findByPreguntaId(Integer id);
}
