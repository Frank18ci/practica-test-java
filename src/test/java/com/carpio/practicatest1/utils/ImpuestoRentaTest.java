package com.carpio.practicatest1.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImpuestoRentaTest {
    @Test
    public void tasa0True() {
        ImpuestoRenta c = new ImpuestoRenta(4000);
        double iR = Math.rint(c.calcularImpuesto() * 100) / 100;
        System.out.println("Tasa 0%: " + iR);
        assertEquals(0.00, c.calcularImpuesto());
    }

    @Test
    public void tasa0False() {
        ImpuestoRenta c = new ImpuestoRenta(4000);
        double iR = Math.rint(c.calcularImpuesto() * 100) / 100;
        System.out.println("Tasa 0%: " + iR);
        assertFalse(c.calcularImpuesto() != 0.00);
    }

    @Test
    public void tasa0Equals() {
        ImpuestoRenta c = new ImpuestoRenta(4000);
        double iR = Math.rint(c.calcularImpuesto() * 100) / 100;
        System.out.println("Tasa 0%: " + iR);
        assertEquals(0.00, c.calcularImpuesto());
    }

    @Test
    public void tasa8True() {
        ImpuestoRenta c = new ImpuestoRenta(30000);
        double iR = Math.rint(c.calcularImpuesto() * 100) / 100;
        System.out.println("Tasa 0%: " + iR);
        assertEquals(0, (double) c.calcularImpuesto());
    }
}
