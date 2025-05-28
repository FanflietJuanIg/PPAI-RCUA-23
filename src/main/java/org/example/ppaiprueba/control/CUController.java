//Controlador que implementa la logica de negocio CUController.java
package org.example.ppaiprueba.control;

import org.example.ppaiprueba.modelo.Empleado;
import org.example.ppaiprueba.modelo.EventoSismico;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.example.ppaiprueba.modelo.Sesion;

import org.example.ppaiprueba.modelo.Estado;
import org.example.ppaiprueba.vista.RegistrarRevisionFController;

import java.awt.image.BufferedImage;

public class CUController {
    private List<EventoSismico> eventosSismicos;
    private List<Estado> estados;
    private Sesion sesion;
    private RegistrarRevisionFController pantalla;

    public CUController(List<EventoSismico> eventosSismicos, List<Estado> estados, Sesion sesion, RegistrarRevisionFController pantalla){
        this.eventosSismicos = eventosSismicos;
        this.estados = estados;
        this.sesion = sesion;
        this.pantalla = pantalla;
    }

    public void buscarEventosSismicos() {
        // Agrega logging para debug
        System.out.println("Total eventos: " + eventosSismicos.size());

        List<EventoSismico> eventosPendientes = eventosSismicos.stream()
                .filter(evento -> evento.esAutodetectado() ||
                                evento.esPendienteRevision())
                .collect(Collectors.toList());

        if (eventosPendientes.isEmpty()) {
            pantalla.mostrarEventosSismicos(null);
        }

        System.out.println("Eventos pendientes encontrados: " + eventosPendientes.size());
        ordenarEventos(eventosPendientes);
    }
// esto es un map de los datos que se le van a pasar a la pantalla, no todos los datos del evento deben ser visualizados
// uso map para no tener que crear otra clase lo cual causaria inconsistencia con el modelo
    public void ordenarEventos(List<EventoSismico> eventosPendientes){
        List<Map<String, Object>> eventosOrdenados = eventosPendientes.stream()
                .sorted(Comparator.comparing(EventoSismico::getFechaHoraOcurrencia).reversed())
                .map(e -> {
                    Map<String, Object> eventosMapeado = new HashMap<>();
                    eventosMapeado.put("Evento", e);
                    eventosMapeado.put("Magnitud", e.getMagnitud());
                    eventosMapeado.put("fecha y hora ocurrencia", e.getFechaHoraOcurrencia());
                    eventosMapeado.put("Latitud Hipocentro", e.getLatitudHipocentro());
                    eventosMapeado.put("Longitud Hipocentro", e.getLongitudHipocentro());
                    eventosMapeado.put("Latitud Epicentro", e.getLatitudEpicentro());
                    eventosMapeado.put("Longitud Epicentro", e.getLongitudEpicentro());
                    return eventosMapeado;
                })
                .toList();
        pantalla.mostrarEventosSismicos(eventosOrdenados);
    }

    public void tomarFechaHoraActual(EventoSismico evento, double num){
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        if (num == 1){
            buscarEstadoRechazado(evento, fechaHoraActual);
        }
        else if (num == 2) {
            buscarEstadoConfirmado(evento, fechaHoraActual);
        }
        else if (num == 3) {
            buscarEstadoEnRevision(evento, fechaHoraActual);
        }
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
        bloquearEventoParaRevision(enRevision, evento, fechaHoraActual);
    }

    public void buscarEstadoConfirmado(EventoSismico evento ,LocalDateTime fechaHoraActual) {
        Estado confirmado = null;
        for (Estado estado : estados) {
            if (estado.esAmbitoEventoSismico() && estado.esConfirmado()) {
                confirmado = estado;
                break;
            }
        }
        buscarEmpleadoLogeado(evento, fechaHoraActual, confirmado);
    }

    public void bloquearEventoParaRevision(Estado enRevision, EventoSismico evento, LocalDateTime fechaHoraActual){
            evento.bloquearParaRevision(enRevision, fechaHoraActual);
            buscarDatosSismicos(evento);
    }

    public void buscarEmpleadoLogeado(EventoSismico evento, LocalDateTime fechaHoraActual ,Estado estado){
        Empleado empleadoLogueado = sesion.getEmpleadoLogueado();

        if (estado.esRechazado()) {
            rechazarEventoSismico(estado, empleadoLogueado, evento, fechaHoraActual);
        }
        else if (estado.esConfirmado()) {
            confirmarEventoSismico(estado, empleadoLogueado, evento, fechaHoraActual);
        }
    }

    public void rechazarEventoSismico(Estado estadoRechazado,Empleado empleadoLogueado, EventoSismico evento, LocalDateTime fechaHoraActual) {
        evento.rechazar(estadoRechazado, empleadoLogueado, fechaHoraActual);
    }

    public void confirmarEventoSismico(Estado estadoRechazado,Empleado empleadoLogueado, EventoSismico evento, LocalDateTime fechaHoraActual) {
        evento.confirmar(estadoRechazado, empleadoLogueado, fechaHoraActual);
    }

//TODO: esto me recomendo chat gpt, se tiene que hacer todo eso
    public void buscarDatosSismicos (EventoSismico evento) {
        Map<String, Object> eventosMapeado = new HashMap<>();
        eventosMapeado.put("Alcance", evento.getAlcance());
        eventosMapeado.put("Clasificacion", evento.getClasificacion());
        eventosMapeado.put("Origen", evento.getOrigen());
        pantalla.mostrarDatosSismicos(eventosMapeado);
        buscarDatosSeriesTemporales(evento);
    }

    public void buscarDatosSeriesTemporales(EventoSismico evento) {
        Object [][] eventosClasificados = evento.buscarDatosSeriesTemp();
        llamarCUGenerarSismograma();
        habilitarMapa(evento);
    }

    public void habilitarMapa(EventoSismico evento) {
        pantalla.habilitarOpcionVerMapa();
        habilitarOpcionModificarDatos(evento);
    }

    public void habilitarOpcionModificarDatos(EventoSismico evento) {
        pantalla.mostrarOpcionModificarDatos(evento);
        habilitarOpcionCambioEstado();
    }

    public void habilitarOpcionCambioEstado(){
        pantalla.mostrarOpcionCambioEstado();
    }

    public void validarDatos(EventoSismico evento, double num) {
        if (evento.getMagnitud() != null && evento.getClasificacion() != null && evento.getOrigen() != null) {
            tomarFechaHoraActual(evento, num);

        }
    }

    private void llamarCUGenerarSismograma () {
        pantalla.mostrarSismograma();

    }
}