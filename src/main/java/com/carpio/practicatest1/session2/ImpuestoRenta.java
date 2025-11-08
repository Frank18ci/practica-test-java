package com.carpio.practicatest1.session2;

public class ImpuestoRenta {
    private double uit = 3950;
    private final double sueldo;
    private double excedente;

    public ImpuestoRenta(double sueldo) {
        this.sueldo = sueldo;
    }

    public double calcularImpuesto() {
        double iR;
        if (sueldo < (uit * 7)) {
            return iR = 0;
        } else if (sueldo >= (uit * 7) && sueldo <= (uit * 12)) {
            excedente = sueldo - (uit * 7);
            iR = excedente * 0.08;
            return iR;
        } else if (sueldo > (uit * 12) && sueldo <= (uit * 27)) {
            excedente = sueldo - (uit * 12);
            iR = excedente * 0.14 + uit * 5 * 0.08;
            return iR;
        } else if (sueldo > (uit * 27) && sueldo <= (uit * 42)) {
            excedente = sueldo - (uit * 27);
            iR = excedente * 0.17 + uit * 15 * 0.14 + uit * 5 * 0.08;
            return iR;
        } else if (sueldo > (uit * 42) && sueldo <= (uit * 52)) {
            excedente = sueldo - (uit * 42);
            iR = excedente * 0.20 + uit * 15 * 0.17 + uit * 15 * 0.14 + uit * 5 * 0.08;
            return iR;
        } else {
            excedente = sueldo - (uit * 52);
            iR = excedente * 0.30 + uit * 10 * 0.20 + uit * 15 * 0.17 + uit * 15 * 0.14 + uit * 5 * 0.08;
            return iR;
        }
    }
}
