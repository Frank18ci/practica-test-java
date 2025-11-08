package com.carpio.practicatest1.session2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ImpuestoRentaTest {
    @ParameterizedTest
    @CsvSource({
            "4000, 0.00",
            "30000, 188.00",
            "50000, 1944.00",
            "120000, 12144.50",
            "200000, 26767.50",
            "300000, 56227.50"
    })
    public void calcularImpuesto_csv(double sueldo, double expected) {
        ImpuestoRenta c = new ImpuestoRenta(sueldo);
        double actual = Math.rint(c.calcularImpuesto() * 100) / 100;
        System.out.println(Math.rint(c.calcularImpuesto() * 100));
        System.out.println(c.calcularImpuesto() * 100);
        System.out.println("sueldo: " + sueldo + " -> impuesto: " + actual);
        assertEquals(expected, actual, 0.001);
    }
}
