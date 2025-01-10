package com.prapp.pt_crud_ecommerce.excepcion;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {
    private LocalDateTime fecha;
    private String mensaje;
    private String detalle;


    public ExceptionResponse() {
    }
    public ExceptionResponse(LocalDateTime fecha, String mensaje, String detalle) {
        super();
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.detalle = detalle;

    }

}
