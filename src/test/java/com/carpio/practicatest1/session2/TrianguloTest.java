package com.carpio.practicatest1.session2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TrianguloTest {
    @ParameterizedTest
    @CsvSource({
            "0, 20, 20, 0",
            "20, 20, 20, 1",
            "20, 20, 10, 2",
            "10, 20, 25, 3"
    })
    public void verificationTriangulo(Integer a, Integer b, Integer c, Integer expected) {
        Triangulo triangulo = new Triangulo(a, b, c);
        Integer actual = triangulo.validarTipoTriangulo();
        assertEquals(expected, actual);
    }


}
