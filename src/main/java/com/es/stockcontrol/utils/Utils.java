package com.es.stockcontrol.utils;

import java.util.List;

public class Utils {

    public static boolean verificarLista(List<Object> lista) {
        // Comprobamos si la lista es nula o está vacía
        if (lista == null || lista.isEmpty()) {
            return false; // Si es nula o vacía, devuelve false
        }

        // Recorremos cada elemento de la lista
        for (Object elemento : lista) {
            // Comprobamos si el elemento es nulo
            if (elemento == null) {
                return false; // Si hay un elemento nulo, devuelve false
            }

            // Si es un String, verificamos si está vacío
            if (elemento instanceof String && ((String) elemento).isEmpty()) {
                return false; // Si el String está vacío, devuelve false
            }

            // Si es un Integer, verificamos si es negativo
            if (elemento instanceof Integer && ((Integer) elemento) < 0) {
                return false; // Si el Numero es negativo returnea false
            }
        }

        // Si todos los elementos son válidos, devuelve true
        return true;
    }
}