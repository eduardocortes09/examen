package com.calificaciones.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "opcion_preguntas")
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pregunta pregunta;


    @Column(name = "opcion", nullable = false, length = 100)
    private String opcion;

    @Column(name = "correcta", nullable = false, length = 100)
    private Integer correcta;


}
