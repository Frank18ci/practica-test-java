package com.carpio.practicatest1.utils;

public class Suma {
    private final int a;
    private final int b;

    public Suma(int a, int b) {
        this.a = a;
        this.b = b;
    }

    // Suma simple con comportamiento de int (posible wrap-around)
    public int sumar() {
        return a + b;
    }

    // Suma segura: lanza ArithmeticException en overflow
    public int sumarChecked() {
        return Math.addExact(a, b);
    }
}
