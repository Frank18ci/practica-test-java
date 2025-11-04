package com.carpio.practicatest1.utils;

public class ImpuestoRenta {
    private Double uit = 4650.00;
    private Double sueldo;
    private Double excedente;

    public ImpuestoRenta(double sueldo) {
        this.sueldo = sueldo;
    }
    public Double calcularImpuesto() {
        Double ir = 0.0;
        if(sueldo < (uit * 7)){
            ir = 0.0;
        } else if(sueldo >= (uit * 7) && sueldo <= (uit * 12)) {
            ir = excedente * 0.08;
        } else if(sueldo > (uit * 12) && sueldo <= (uit * 27)){
            ir = excedente * 0.14;
        } else if(sueldo > (uit * 27) && sueldo <= (uit * 42)){
            ir = excedente * 0.17;
        } else if(sueldo > (uit * 42) && sueldo <= (uit * 52)){
            ir = excedente * 0.17;
        } else {
            ir = excedente * 0.3;
        }
        return ir;
    }
}
