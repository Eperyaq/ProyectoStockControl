package com.es.stockcontrol.utils;

import java.util.List;

public class Utils {

    public static boolean verificarLista(List<String> lista) {
        // Comprobamos si la lista es nula o está vacía
        if (lista == null || lista.isEmpty()) {
            return false; // Si es nula o vacía, devuelve false
        }

        for (String elemento : lista) {
            if (elemento == null || elemento.isEmpty()) {
                return false;
            }
        }

        // Si todos los elementos son válidos, devuelve true
        return true;
    }
}