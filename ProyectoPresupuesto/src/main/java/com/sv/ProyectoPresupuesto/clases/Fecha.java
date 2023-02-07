package com.sv.ProyectoPresupuesto.clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

@Data
public class Fecha {

    Date fechaActual = new Date();

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

    private String fechaRegistro = formato.format(fechaActual);
}
