//Controlador que implementa la logica de negocio CUController.java
package org.example.ppaiprueba.control;


import org.example.ppaiprueba.modelo.CambioEstado;
import org.example.ppaiprueba.modelo.EventoSismico;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.example.ppaiprueba.modelo.Estado;
import org.example.ppaiprueba.modelo.Usuario;


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
    public LocalDateTime TomarFechaHoraActual() {
        return LocalDateTime.now();
    }

    public String buscarASlogueado() {
        Usuario usuario = Usuario.getUsuarioLogueado();
        if (usuario != null && "Analista en Sismos".equals(usuario.getEmpleado().getRol())) {
            return usuario.getNombreUsuario();
        }
        throw new IllegalStateException("No hay un Analista en Sismos logueado.");
    }
    public void RechazarEventoSismico(EventoSismico eventoSismico) {
        // Buscar y obtener el estado rechazado
        Estado estadoRechazado = Estado.BuscarEstadoRechazado();

        if (!estadoRechazado.esAmbitoEventoSismico()) {
            throw new IllegalArgumentException("El estado 'Rechazado' no es válido para el evento.");
        }

        // Actualizar el estado a "Rechazado"
        eventoSismico.setEstado("Rechazado");

        // Cerrar el estado anterior
        eventoSismico.cerrarEstadoActual(TomarFechaHoraActual());

        // Crear un nuevo cambio de estado
        CambioEstado nuevoCambio = new CambioEstado(TomarFechaHoraActual(),estadoRechazado);
        eventoSismico.agregarCambioEstado(nuevoCambio);

        // Registrar la fecha de revisión
        eventoSismico.setFechaRevision(TomarFechaHoraActual());

        // Asignar el AS logueado como responsable
        String asLogueado = buscarASlogueado();
        eventoSismico.setResponsableRevision(asLogueado);

        System.out.println("Evento rechazado exitosamente. Responsable: " + asLogueado + ", Fecha: " + TomarFechaHoraActual());
    }
}

