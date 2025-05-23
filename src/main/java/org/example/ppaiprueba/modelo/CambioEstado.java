package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;

public class CambioEstado {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private Estado estado;

    public CambioEstado(LocalDateTime inicio, Estado estado) {
        this.fechaHoraInicio = inicio;
        this.estado = estado;
    }

    public boolean esActual() {
        return fechaHoraFin == null;
    }

    public void setFechaHoraFin(LocalDateTime fin) {
        this.fechaHoraFin = fin;
    }

    public Estado getEstado() {
        return estado;
    }
}
