package org.example.ppaiprueba.modelo;

public class ClasificacionSismo {
    private double kmProfundidadDesde;
    private double kmProfundidadHasta;
    public enum Nombre{
        SUPERFICIAL,
        INTERMEDIO,
        PROFUNDO
    };

    private Nombre nombre;

    public ClasificacionSismo(double desde, double hasta, Nombre nombre) {
        this.kmProfundidadDesde = desde;
        this.kmProfundidadHasta = hasta;
        this.nombre = nombre;
    }

    public double getKmProfundidadDesde() {
        return kmProfundidadDesde;
    }

    public double getKmProfundidadHasta() {
        return kmProfundidadHasta;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public void setKmProfundidadDesde(double desde) {
        this.kmProfundidadDesde = desde;
    }

    public void setKmProfundidadHasta(double hasta) {
        this.kmProfundidadHasta = hasta;
    }

    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }
}
