package com.carpio.practicatest1.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SumaTest {
    @Test
    void sumaPositivos() {
        Suma suma = new Suma(2, 3);
        assertEquals(5, suma.sumar());
    }

    @Test
    void sumaNegativos() {
        Suma suma = new Suma(-2, -3);
        assertEquals(-5, suma.sumar());
    }

    @Test
    void sumaPositivoNegativo() {
        Suma suma = new Suma(2, -3);
        assertEquals(-1, suma.sumar());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "5, 0, 5",
            "0, -5, -5",
            "123, -23, 100",
            "-10, 15, 5"
    })
    void sumaCasosParametrizados(int a, int b, int esperado) {
        Suma s = new Suma(a, b);
        assertEquals(esperado, s.sumar());
    }

    @Test
    void sumaConCero() {
        Suma s = new Suma(0, 0);
        assertEquals(0, s.sumar());
    }

    @Test
    void sumaOverflowWraps() {
        Suma s = new Suma(Integer.MAX_VALUE, 1);
        // Comportamiento de int: wrap-around a Integer.MIN_VALUE
        assertEquals(Integer.MIN_VALUE, s.sumar());
    }

    @Test
    void sumaCheckedDetectaOverflow() {
        Suma s = new Suma(Integer.MAX_VALUE, 1);
        assertThrows(ArithmeticException.class, s::sumarChecked);
    }

    @Test
    void sumaCheckedNormal() {
        Suma s = new Suma(100, 23);
        assertEquals(123, s.sumarChecked());
    }
}