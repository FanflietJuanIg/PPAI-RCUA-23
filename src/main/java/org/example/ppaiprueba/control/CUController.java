//Controlador que implementa la logica de negocio CUController.java
package org.example.ppaiprueba.control;

import org.example.ppaiprueba.modelo.EventoSismico;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.example.ppaiprueba.modelo.Estado;

public class CUController {
    private List<EventoSismico> eventosPendientes;

    public CUController(List<EventoSismico> eventos) {
        this.eventosPendientes = eventos;
    }

    public List<EventoSismico> obtenerEventosPendientes() {
        return eventosPendientes.stream()
                .filter(EventoSismico::esPendienteRevision)
                .toList();
    }

    public List<Map<String, Object>> OrdenarEventos(){
        return obtenerEventosPendientes().stream()
                .sorted(Comparator.comparing(EventoSismico::getFechaHoraOcurrencia).reversed())
                .map(e -> {
                    Map<String, Object> mapa = new HashMap<>();
                    mapa.put("magnitud", e.obtenerMagnitud());
                    mapa.put("fecha y hora ocurrencia", e.getFechaHoraOcurrencia());
                    mapa.put("Latitud Hipocentro", e.getLatitudHipocentro());
                    mapa.put("Longitud Hipocentro", e.getLongitudHipocentro());
                    mapa.put("Latitud Epicentro", e.getLatitudEpicentro());
                    mapa.put("Longitud Epicentro", e.getLongitudEpicentro());
                    return mapa;
                })
                .collect(Collectors.toList());
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
