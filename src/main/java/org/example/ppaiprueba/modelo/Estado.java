package org.example.ppaiprueba.modelo;

public class Estado {
    private String nombre;

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public boolean esAutodetectado() {
        return nombre.equalsIgnoreCase("Autodetectado");
    }

    public boolean esPendienteRevision() {
        return nombre.equalsIgnoreCase("Pendiente Revision");
    }

    public boolean esEnRevision() {
        return nombre.equalsIgnoreCase("En Revision");
    }

    public boolean esRechazado() {
        return nombre.equalsIgnoreCase("Rechazado");
    }
}