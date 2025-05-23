package org.example.ppaiprueba.modelo;

public class ClasificacionSismo {
    private double kmProfundidadDesde;
    private double kmProfundidadHasta;
    private String nombre;

    public ClasificacionSismo(double desde, double hasta, String nombre) {
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

    public String getNombre() {
        return nombre;
    }

    public void setKmProfundidadDesde(double desde) {
        this.kmProfundidadDesde = desde;
    }

    public void setKmProfundidadHasta(double hasta) {
        this.kmProfundidadHasta = hasta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
