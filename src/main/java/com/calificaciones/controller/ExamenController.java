package com.calificaciones.controller;

import com.calificaciones.model.ResponseGeneral;
import com.calificaciones.servicio.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
public class ExamenController {

    @Autowired
    private ExamenService examenService;



    @PostMapping(path = "/crearAlumno/{nombre}/{edad}/{ciudad}/{zonaHoraria}")
    public ResponseGeneral creaAlumno
            (HttpServletRequest servletRequest, @PathVariable("nombre") String nombre, @PathVariable("edad") Integer edad, @PathVariable("ciudad") String ciudad,
             @PathVariable("zonaHoraria") String zonaHoraria) {

        ResponseGeneral response = examenService.crearAlumno(nombre, edad, ciudad, zonaHoraria);


        return response;
    }


    @PostMapping(path = "/crearExamen/{nombrePregunta}")
    public ResponseGeneral creaExamen
            (HttpServletRequest servletRequest, @PathVariable("nombrePregunta") String nombrePregunta) {

        ResponseGeneral response = examenService.crearExamen(nombrePregunta);


        return response;
    }


    @GetMapping(path = "/listaPreguntas")
    public ResponseGeneral listadoCalificaciones
            (HttpServletRequest servletRequest) {
        ResponseGeneral response = examenService.obtenerPreguntas();
        return response;
    }

    @PostMapping(path = "/creaOpcionesPorPregunta/{idPregunta}/{opcion}/{correcta}")
    public ResponseGeneral creaOpcionesPorPregunta
            (HttpServletRequest servletRequest, @PathVariable("idPregunta") Integer idPregunta,
             @PathVariable("opcion") String opcion, @PathVariable("correcta") Integer correcta) {
        ResponseGeneral response = examenService.crearOpcionesPregunta(idPregunta,opcion, correcta);
        return response;
    }


    @PostMapping(path = "/hacerExamen/{idAlumno}/{idPregunta}/{idOpcion}")
    public ResponseGeneral hacerExamen
            (HttpServletRequest servletRequest, @PathVariable("idAlumno") Integer idAlumno,  @PathVariable("idPregunta") Integer idPregunta,
             @PathVariable("idOpcion") Long idOpcion) {
        ResponseGeneral response = examenService.hacerExamen(idAlumno,idPregunta, idOpcion);
        return response;
    }


    @GetMapping(path = "/getCalificacionPorAlumno/{idAlumno}")
    public ResponseGeneral getCalificacionAlumno
            (HttpServletRequest servletRequest , @PathVariable("idAlumno") Long idAlumno) {
        ResponseGeneral response = examenService.daCalificacionAlumno(idAlumno);
        return response;
    }

    //America/Bogota

    @GetMapping(path = "/getZonaHoraria/{anio}/{mes}/{dia}/{hora}")
    public ResponseGeneral getZonaLocal
            (HttpServletRequest servletRequest , @PathVariable("anio")  Integer anio , @PathVariable("mes")  Integer mes,
                     @PathVariable("dia")  Integer dia, @PathVariable("hora")  Integer hora) {
        ResponseGeneral response = examenService.daHoraLocal(anio, mes, dia, hora);
        return response;

    }











}