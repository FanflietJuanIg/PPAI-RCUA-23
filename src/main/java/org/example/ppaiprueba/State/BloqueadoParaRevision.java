package org.example.ppaiprueba.State;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.example.ppaiprueba.modelo.CambioEstado;
import org.example.ppaiprueba.modelo.Empleado;
import org.example.ppaiprueba.modelo.EventoSismico;

import java.time.LocalDate;

@Entity
@Table(name = "EstadoBloqueadoParaRevision")
public class BloqueadoParaRevision extends Estado {
    public BloqueadoParaRevision () {
        super("bloqueado para revision");
    }

/*
    public Estado newEstadoEventoSismico() {
        return new Rechazado();
    }

 */
    public void rechazar(LocalDate fecha, Empleado responsable, EventoSismico evento) {
        Estado rechazado = new Rechazado();

        CambioEstado cambio = newCambioEstado(fecha,rechazado, responsable, evento);

        evento.addCambioEstado(cambio);
        evento.setEstadoActual(rechazado);

    }

    public void confirmar(LocalDate fecha, Empleado responsable, EventoSismico evento) {
        Estado confirmado = new Confirmado();

        CambioEstado cambio = newCambioEstado(fecha,confirmado, responsable, evento);

        evento.addCambioEstado(cambio);
        evento.setEstadoActual(confirmado);

    }

    public CambioEstado newCambioEstado(LocalDate fechaHoraActual, Estado estado, Empleado empleado, EventoSismico evento) {
        for (CambioEstado ce : evento.getCambiosEstado()) {
            if (ce.esActual()) {
                ce.setFechaHoraFin(fechaHoraActual);
                break;
            }
        }
        CambioEstado cambio = new CambioEstado(fechaHoraActual, estado, empleado);
        return cambio;
    }
}

