package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;

public class CambioEstado {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private Estado estado;
    private Empleado empleado;

    public CambioEstado(LocalDateTime inicio, Estado estado, Empleado empleado) {
        this.fechaHoraInicio = inicio;
        this.estado = estado;
        this.empleado = empleado;
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

    public void setResponsable(Empleado empleado) {this.empleado = empleado;}

    public Empleado getResponsable() {return empleado;}
}
