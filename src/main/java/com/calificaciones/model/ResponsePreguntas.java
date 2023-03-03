package com.calificaciones.model;

import com.calificaciones.entity.Opcion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePreguntas{

    private Integer id;
    private String pregunta;

    private List<Opcion> listOpcion;

}
