package org.example.ppaiprueba.modelo;

import org.example.ppaiprueba.State.Estado;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CambioEstado {
    private LocalDate fechaHoraInicio;
    private LocalDate fechaHoraFin;
    private Estado estado;
    private Empleado empleado;

    public CambioEstado(LocalDate inicio, Estado estado, Empleado empleado) {
        this.fechaHoraInicio = inicio;
        this.estado = estado;
        this.empleado = empleado;
    }

    public boolean esActual() {
        return fechaHoraFin == null;
    }

    public void setFechaHoraFin(LocalDate fin) {
        this.fechaHoraFin = fin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setResponsable(Empleado empleado) {this.empleado = empleado;}

    public Empleado getResponsable() {return empleado;}
}
