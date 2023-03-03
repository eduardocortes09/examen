package com.calificaciones.servicio;

import com.calificaciones.model.ResponseGeneral;

import java.time.ZonedDateTime;

public interface ExamenService {

    public ResponseGeneral crearAlumno(String nombre, Integer edad,  String ciudad, String zonaHoraria);

    public ResponseGeneral crearExamen(String nombrePregunta);

    public ResponseGeneral obtenerPreguntas();

    public ResponseGeneral crearOpcionesPregunta(Integer IdPregunta, String opcion, Integer correcta);

    public ResponseGeneral hacerExamen(Integer idAlumno,Integer idPregunta, Long idOpcion);

    public ResponseGeneral daCalificacionAlumno(Long idAlumno);

    public ResponseGeneral daHoraLocal(Integer anio, Integer mes, Integer dia, Integer hora);

}
