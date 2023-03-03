package com.calificaciones.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGeneral {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private String message;
    private int code;
    private Object response;
}
