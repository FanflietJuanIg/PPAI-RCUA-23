package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoSismico {
    private double magnitud;
    private String alcance;
    private String origen;
    private String estado;
    private LocalDateTime fechaRevision;
    private String responsableRevision;

    public EventoSismico(double magnitud, String alcance, String origen, String estado) {
        this.magnitud = magnitud;
        this.alcance = alcance;
        this.origen = origen;
        this.estado = estado;
    }

    // Getters y Setters
    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(LocalDateTime fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getResponsableRevision() {
        return responsableRevision;
    }

    public void setResponsableRevision(String responsableRevision) {
        this.responsableRevision = responsableRevision;
    }

    @Override
    public String toString() {
        return "Magnitud " + magnitud + " - Origen: " + origen;
    }

    // Datos simulados
    public static List<EventoSismico> generarEventosSimulados() {
        List<EventoSismico> lista = new ArrayList<>();
        lista.add(new EventoSismico(4.5, "Regional", "Chile", "Pendiente"));
        lista.add(new EventoSismico(5.8, "Nacional", "Perú", "Pendiente"));
        lista.add(new EventoSismico(3.2, "Local", "Argentina", "Confirmado")); // Este no aparecerá
        return lista;
    }
}
