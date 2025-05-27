//Controlador que implementa la logica de negocio CUController.java
package org.example.ppaiprueba.control;

import org.example.ppaiprueba.modelo.Empleado;
import org.example.ppaiprueba.modelo.EventoSismico;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.example.ppaiprueba.modelo.Sesion;

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
// esto es un map de los datos que se le van a pasar a la pantalla, no todos los datos del evento deben ser visualizados
// uso map para no tener que crear otra clase lo cual causaria inconsistencia con el modelo
    public List<Map<String, Object>> OrdenarEventos(){
        return obtenerEventosPendientes().stream()
                .sorted(Comparator.comparing(EventoSismico::getFechaHoraOcurrencia).reversed())
                .map(e -> {
                    Map<String, Object> mapa = new HashMap<>();
                    mapa.put("magnitud", e.getMagnitud());
                    mapa.put("fecha y hora ocurrencia", e.getFechaHoraOcurrencia());
                    mapa.put("Latitud Hipocentro", e.getLatitudHipocentro());
                    mapa.put("Longitud Hipocentro", e.getLongitudHipocentro());
                    mapa.put("Latitud Epicentro", e.getLatitudEpicentro());
                    mapa.put("Longitud Epicentro", e.getLongitudEpicentro());
                    return mapa;
                })
                .collect(Collectors.toList());
    }

    /*
      public void confirmarEvento(EventoSismico evento, Sesion sesion) {

          evento.setEstadoActual("Confirmado"); //Tenemos que pasarle un objeto Estado creado previamente
          evento.setFechaRevision(LocalDateTime.now());
          evento.setResponsableRevision(analista);


        Estado nuevoEstado = new Estado(Estado.Tipo.EN_REVISION, Estado.Ambito.EVENTO_SISMICO);
        evento.cambiarEstado(nuevoEstado, sesion.getEmpleadoLogueado());
    }


    public void derivarEvento(EventoSismico evento, String analista) {
        evento.setEstado("Derivado");
        evento.setFechaRevision(LocalDateTime.now());
        evento.setResponsableRevision(analista);
    }
      */

    public void rechazarEventoSismico(EventoSismico evento, Sesion sesion) {
        Estado nuevoEstado = new Estado(Estado.Tipo.RECHAZADO, Estado.Ambito.EVENTO_SISMICO); // No se como modelar el estado =)
        evento.cambiarEstado(nuevoEstado, sesion.getEmpleadoLogueado());
    }


}
