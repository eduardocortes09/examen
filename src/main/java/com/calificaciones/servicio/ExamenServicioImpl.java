package com.calificaciones.servicio;

import com.calificaciones.entity.Alumno;
import com.calificaciones.entity.Opcion;
import com.calificaciones.entity.Pregunta;
import com.calificaciones.entity.Respuesta;
import com.calificaciones.model.ResponseGeneral;
import com.calificaciones.model.ResponsePreguntas;
import com.calificaciones.model.ResponseCalificaciones;
import com.calificaciones.repository.AlumnoRepository;
import com.calificaciones.repository.OpcionRepository;
import com.calificaciones.repository.PreguntaRepository;
import com.calificaciones.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ExamenServicioImpl implements ExamenService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private OpcionRepository opcionRepository;


    @Autowired
    private RespuestaRepository respuestaRepository;


    public ResponseGeneral crearAlumno(String nombre, Integer edad, String ciudad, String zonaHoraria) {


        Alumno alumno = new Alumno();

        alumno.setNombre(nombre);
        alumno.setCiudad(ciudad);
        alumno.setEdad(edad);
        alumno.setZonaHoraria(zonaHoraria);

        alumnoRepository.save(alumno);

        ResponseGeneral responseGeneral = new ResponseGeneral();

        responseGeneral.setCode(201);
        responseGeneral.setMessage("sucess");
        responseGeneral.setResponse("Alumno: " + alumno.getNombre() + " creada!");

        return responseGeneral;
    }


    public ResponseGeneral crearExamen(String nombrePregunta) {

        Pregunta pregunta = new Pregunta();

        pregunta.setPregunta(nombrePregunta);

        preguntaRepository.save(pregunta);

        ResponseGeneral responseGeneral = new ResponseGeneral();

        responseGeneral.setCode(201);
        responseGeneral.setMessage("sucess");
        responseGeneral.setResponse("Pregunta: " + pregunta.getPregunta() + " creado!");

        return responseGeneral;
    }


    @Override
    public ResponseGeneral obtenerPreguntas() {

        ResponseGeneral responseGeneral = new ResponseGeneral();

        List<Pregunta> listPreguntas = preguntaRepository.findAll();

        List<ResponsePreguntas> listResponsePreguntas = new ArrayList<>();

        listPreguntas.stream().forEach((p) -> {
            List<Opcion> listOpciones = new ArrayList<>();

            List<Opcion> listOpcion = opcionRepository.findByPreguntaId(p.getId());

            listOpcion.stream().forEach((opt) -> {
                Opcion opcion = new Opcion();
                opcion.setId(opt.getId());
                opcion.setOpcion(opt.getOpcion());
                opcion.setCorrecta(opt.getCorrecta());
                opcion.setPregunta(null);
                listOpciones.add(opcion);
            });

            ResponsePreguntas responsePreguntas = new ResponsePreguntas();

            responsePreguntas.setId(p.getId());
            responsePreguntas.setPregunta(p.getPregunta());

            responsePreguntas.setListOpcion(listOpciones);

            listResponsePreguntas.add(responsePreguntas);
        });

        responseGeneral.setCode(200);
        responseGeneral.setMessage("sucess");
        responseGeneral.setResponse(listResponsePreguntas);

        return responseGeneral;
    }


    public ResponseGeneral crearOpcionesPregunta(Integer idPregunta, String opcionPregunta, Integer correcta) {

        ResponseGeneral responseGeneral = new ResponseGeneral();

        Optional<Pregunta> preguntas = preguntaRepository.findById(idPregunta);

        preguntas.ifPresent(ques -> {
            Opcion opcion = new Opcion();
            opcion.setOpcion(opcionPregunta);
            opcion.setPregunta(preguntas.get());
            opcion.setCorrecta(correcta);

            opcionRepository.save(opcion);
        });

        if (!preguntas.isPresent()) {

            responseGeneral.setCode(401);
            responseGeneral.setMessage("not found");
            responseGeneral.setResponse("");

        } else {
            responseGeneral.setCode(201);
            responseGeneral.setMessage("sucess");
            responseGeneral.setResponse("OK:");
        }

        return responseGeneral;
    }


    public ResponseGeneral hacerExamen(Integer idAlumno, Integer idPregunta, Long idOpcion) {

        ResponseGeneral responseGeneral = new ResponseGeneral();

        Respuesta respuesta = new Respuesta();

        respuesta.setAlumnoId(idAlumno.longValue());
        respuesta.setPreguntaId(idPregunta);
        respuesta.setOpcionId(idOpcion);

        Optional<Pregunta> preguntas = preguntaRepository.findById(idPregunta);

        preguntas.ifPresent(ques -> {

            Optional<Opcion> opcion = opcionRepository.findByIdAndPreguntaId(idOpcion, idPregunta);

            opcion.ifPresent(opt -> {

                if (opt.getCorrecta() == 1) {
                    respuesta.setPorcentaje(25);
                } else {
                    respuesta.setPorcentaje(0);
                }

                respuestaRepository.save(respuesta);
            });

            if (!opcion.isPresent() || !preguntas.isPresent()) {
                responseGeneral.setCode(401);
                responseGeneral.setMessage("NOT FOUND");
                responseGeneral.setResponse(":");
            } else {
                responseGeneral.setCode(201);
                responseGeneral.setMessage("sucess");
                responseGeneral.setResponse("Pregunta:");

            }
        });

        return responseGeneral;

    }


    public ResponseGeneral daCalificacionAlumno(Long idAlumno) {

        ResponseGeneral responseGeneral = new ResponseGeneral();

        List<ResponseCalificaciones> listResponseCalificaciones = new ArrayList<>();

        Optional<Alumno> listAlumno = alumnoRepository.findById(idAlumno);

        List<Respuesta> listRespuesta = respuestaRepository.getByAlumnoId(idAlumno);

        ResponseCalificaciones responseCalif = new ResponseCalificaciones();

        responseCalif.setNombre(listAlumno.get().getNombre());

        int promedioFinal = calculaCalificacion(listRespuesta.size(), listRespuesta);

        responseCalif.setCalificacion(promedioFinal);

        listResponseCalificaciones.add(responseCalif);

        responseGeneral.setCode(200);
        responseGeneral.setMessage("sucess");
        responseGeneral.setResponse(listResponseCalificaciones);

        return responseGeneral;

    }


    public ResponseGeneral daHoraLocal(Integer anio, Integer mes, Integer dia, Integer hora) {

        ZonedDateTime zonaBogota = ZonedDateTime.of(anio, mes, dia, hora, 00, 00, 00, ZoneId.of("America/Bogota"));

        ZonedDateTime fechaNueva = zonaBogota.withZoneSameInstant(ZoneId.of("America/Mexico_City"));

        ResponseGeneral responseGeneral = new ResponseGeneral();

        responseGeneral.setCode(200);
        responseGeneral.setMessage("sucess");
        responseGeneral.setResponse("hora local:" + fechaNueva);

        return responseGeneral;

    }

    public Integer calculaCalificacion(Integer numero, List<Respuesta> respuesta) {

        int suma = 0;
        int calificacionD;

        for (int i = 0; i <= numero; i++) {

            if (i <= numero - 1) {
                calificacionD = respuesta.get(i).getPorcentaje();
                suma = suma + calificacionD;
            }
        }
        return suma;
    }

}