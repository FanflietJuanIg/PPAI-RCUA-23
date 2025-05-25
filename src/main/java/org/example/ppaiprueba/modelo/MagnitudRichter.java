package org.example.ppaiprueba.modelo;

public class MagnitudRichter {
    private String descripcionMagnitud;
    private Double numero;

    public MagnitudRichter(String descripcionMagnitud, Double numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero;
    }

    public String getDescripcionMagnitud() {
        return descripcionMagnitud;
    }

    public Double getNumero() {
        return numero;
    }

    public void setDescripcionMagnitud(String descripcionMagnitud) {
        this.descripcionMagnitud = descripcionMagnitud;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }
}
