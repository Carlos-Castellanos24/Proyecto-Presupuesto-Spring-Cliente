package com.sv.ProyectoPresupuesto.clases;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Numero {

    public static int getInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static String generarNumeroCuenta() {
        String numeroCuenta = IntStream.rangeClosed(0, 10)
                .mapToObj(i -> Integer.toString(getInt(0, 10)))
                .collect(Collectors.joining(""));
        return numeroCuenta;
    }
}
