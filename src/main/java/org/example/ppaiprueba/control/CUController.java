//Controlador que implementa la logica de negocio CUController.java
package org.example.ppaiprueba.control;

import org.example.ppaiprueba.modelo.EventoSismico;
import java.time.LocalDateTime;
import java.util.List;

public class CUController {
    private List<EventoSismico> eventosPendientes;

    public CUController(List<EventoSismico> eventos) {
        this.eventosPendientes = eventos;
    }

    public List<EventoSismico> obtenerEventosPendientes() {
        return eventosPendientes.stream()
                .filter(e -> e.getEstado().equals("Pendiente"))
                .toList();
    }

    public void confirmarEvento(EventoSismico evento, String analista) {
        evento.setEstado("Confirmado");
        evento.setFechaRevision(LocalDateTime.now());
        evento.setResponsableRevision(analista);
    }

    public void rechazarEvento(EventoSismico evento, String analista) {
        evento.setEstado("Rechazado");
        evento.setFechaRevision(LocalDateTime.now());
        evento.setResponsableRevision(analista);
    }

    public void derivarEvento(EventoSismico evento, String analista) {
        evento.setEstado("Derivado");
        evento.setFechaRevision(LocalDateTime.now());
        evento.setResponsableRevision(analista);
    }
}
