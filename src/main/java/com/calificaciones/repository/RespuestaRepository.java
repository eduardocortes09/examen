package com.calificaciones.repository;

import com.calificaciones.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RespuestaRepository  extends JpaRepository<Respuesta, Integer> {

    List<Respuesta> getByAlumnoId(Long idAlumno);
}
