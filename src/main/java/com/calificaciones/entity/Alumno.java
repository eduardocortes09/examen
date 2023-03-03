package com.calificaciones.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "alumnos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Column(name = "edad", nullable = false, length = 80)
    private Integer edad;

    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @Column(name = "zonaHoraria", nullable = false, length = 150)
    private String zonaHoraria;

}
