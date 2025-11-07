package org.example.ppaiprueba.State;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.example.ppaiprueba.modelo.CambioEstado;
import org.example.ppaiprueba.modelo.Empleado;
import org.example.ppaiprueba.modelo.EventoSismico;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "EstadoAutoDetectado")
public class AutoDetectado extends Estado {

    public AutoDetectado() {
        super("AutoDetectado");
    }

    public Estado newEstadoEventoSismico() {
        return new BloqueadoParaRevision();
    }

    public void bloquearParaRevision(LocalDate fecha, EventoSismico evento) {
        Estado bloqueado = newEstadoEventoSismico();

        Empleado empleadoCambio = null;

        CambioEstado cambio = newCambioEstado(fecha,bloqueado, empleadoCambio, evento);

        evento.addCambioEstado(cambio);
        evento.setEstadoActual(bloqueado);
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
