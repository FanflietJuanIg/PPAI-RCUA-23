package org.example.ppaiprueba.modelo;

public class MagnitudRichter {
    private String descripcionMagnitud;
    private double numero;

    public MagnitudRichter(String descripcionMagnitud, double numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero;
    }

    public String getDescripcionMagnitud() {
        return descripcionMagnitud;
    }

    public double getNumero() {
        return numero;
    }

    public void setDescripcionMagnitud(String descripcionMagnitud) {
        this.descripcionMagnitud = descripcionMagnitud;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }
}
