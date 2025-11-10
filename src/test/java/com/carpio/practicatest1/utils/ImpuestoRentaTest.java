package com.carpio.practicatest1.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImpuestoRentaTest {
    @ParameterizedTest
    @CsvSource({
            "4000, 0.00",
            "30000, 0.00",
            "70000, 2240.00",
            "150000, 7840.00",
            "250000, 15940.00",
            "350000, 25440.00",
            "600000, 75440.00"
    })
    public void testCalculateImpuestoRenta() {
        ImpuestoRenta c = new ImpuestoRenta(4000);
        double iR = Math.rint(c.calcularImpuesto() * 100) / 100;
        System.out.println("Impuesto Renta: " + iR);
        assertEquals(0.00, c.calcularImpuesto());
    }
}
