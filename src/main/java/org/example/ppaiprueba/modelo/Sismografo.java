package org.example.ppaiprueba.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sismografo {
    private LocalDate fechaAdquisicion;
    private String identificadorSismografo;
    private String nroSerie;
    private Estado estadoActual;
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    // Referencia a una estación sismológica
    private EstacionSismologica estacionSismologica;

    public Sismografo(LocalDate fechaAdquisicion, String identificadorSismografo, String nroSerie,
                      Estado estadoActual, EstacionSismologica estacionSismologica) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.estadoActual = estadoActual;
        this.estacionSismologica = estacionSismologica;
    }

    public String getIdentificadorSismografo() {
        return identificadorSismografo;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    public void agregarCambioEstado(CambioEstado cambio) {
        for (CambioEstado ce : cambiosEstado) {
            if (ce.esActual()) {
                ce.setFechaHoraFin(LocalDateTime.now());
            }
            // Otros getters pueden añadirse si necesitás trabajar con más atributos
        }
    }
}