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
    private List<EventoSismico> eventosSismicos;
    private List<Estado> estados;
    private Sesion sesion;

    public void obtenerEventosPendientes() {
        List<EventoSismico> eventosPendientes = eventosSismicos.stream()
                .filter(EventoSismico::esPendienteRevision)
                .filter(EventoSismico::esAutodetectado)
                .toList();
        ordenarEventos(eventosPendientes);

    }
// esto es un map de los datos que se le van a pasar a la pantalla, no todos los datos del evento deben ser visualizados
// uso map para no tener que crear otra clase lo cual causaria inconsistencia con el modelo
    public void ordenarEventos(List<EventoSismico> eventosPendientes){
        List<Map<String, Object>> eventosOrdenados = eventosPendientes.stream()
                .sorted(Comparator.comparing(EventoSismico::getFechaHoraOcurrencia).reversed())
                .map(e -> {
                    Map<String, Object> eventosMapeado = new HashMap<>();
                    eventosMapeado.put("magnitud", e.getMagnitud());
                    eventosMapeado.put("fecha y hora ocurrencia", e.getFechaHoraOcurrencia());
                    eventosMapeado.put("Latitud Hipocentro", e.getLatitudHipocentro());
                    eventosMapeado.put("Longitud Hipocentro", e.getLongitudHipocentro());
                    eventosMapeado.put("Latitud Epicentro", e.getLatitudEpicentro());
                    eventosMapeado.put("Longitud Epicentro", e.getLongitudEpicentro());
                    return eventosMapeado;
                })
                .toList();
        //TODO: aqui deberia ir el metodo para mostrar a la pantalla, pasando como parametro eventosOrdenados
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
    public void tomarFechaHoraActual(EventoSismico evento){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        buscarEstadoRechazado(evento, fechaHoraActual);
    }

    public void buscarEstadoRechazado(EventoSismico evento ,LocalDateTime fechaHoraActual) {
        Estado rechazado = null;
        for (Estado estado : estados) {
            if (estado.esAmbitoEventoSismico() && estado.esRechazado()) {
                rechazado = estado;
                break;
            }
        }
        buscarEmpleadoLogeado(evento, fechaHoraActual, rechazado);
    }

    public void buscarEstadoEnRevision(EventoSismico evento, LocalDateTime fechaHoraActual){
        Estado enRevision = null;
        for (Estado estado : estados){
            if (estado.esAmbitoEventoSismico() && estado.esEnRevision()){
                enRevision = estado;
                break;
            }
        }
    }

    public void bloquearEventoParaRevision(Estado enRevision, EventoSismico evento, LocalDateTime fechaHoraActual){
            evento.bloquearParaRevision(enRevision, fechaHoraActual);
    }

    public void buscarEmpleadoLogeado(EventoSismico evento, LocalDateTime fechaHoraActual ,Estado rechazado){
        Empleado empleadoLogueado = sesion.getEmpleadoLogueado();
        rechazarEventoSismico(rechazado, empleadoLogueado, evento, fechaHoraActual);
    }
    public void rechazarEventoSismico(Estado estadoRechazado,Empleado empleadoLogueado, EventoSismico evento, LocalDateTime fechaHoraActual) {
        evento.rechazar(estadoRechazado, empleadoLogueado, fechaHoraActual);
    }



}
