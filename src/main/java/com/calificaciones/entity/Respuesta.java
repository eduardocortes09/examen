package com.calificaciones.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "alumno_id", nullable = false)
    private Long alumnoId;

    @Column(name = "pregunta_id", nullable = false)
    private Integer preguntaId;

    @Column(name = "opcion_id", nullable = false)
    private Long opcionId;


    @Column(name = "porcentaje", nullable = false, length = 100)
    private Integer porcentaje;

}
