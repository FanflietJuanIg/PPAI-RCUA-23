//Controlador que implementa la logica de negocio CUController.java
package org.example.ppaiprueba.control;

import org.example.ppaiprueba.modelo.Empleado;
import org.example.ppaiprueba.modelo.EventoSismico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.example.ppaiprueba.modelo.Sesion;

import org.example.ppaiprueba.persistance.Repository;
import org.example.ppaiprueba.vista.PantallaRevision;

public class GestorRevisionManual {
    //private List<EventoSismico> eventosSismicos;
    private Sesion sesion;
    private PantallaRevision pantalla;

    public GestorRevisionManual(Sesion sesion, PantallaRevision pantalla){
        //this.eventosSismicos = eventosSismicos;
        this.sesion = sesion;
        this.pantalla = pantalla;
    }

    public List<EventoSismico> buscarEnBDeventos () {
        Repository<EventoSismico> eventoSismicoRepository = new Repository<>(EventoSismico.class);
        List<EventoSismico> eventoSismicos = eventoSismicoRepository.listarTodos();
        return eventoSismicos;
    }

    public void opRegistrarRevision(){
        buscarEventosSismicos();
    }

    //Paso 6
    public void buscarEventosSismicos() {
        List<EventoSismico> eventoSismicos = buscarEnBDeventos();

        // Agrega logging para debug
        System.out.println("Total eventos: " + eventoSismicos.size());

        List<EventoSismico> eventosPendientes = eventoSismicos.stream()
                .filter(evento -> evento.esAutodetectado())
                .collect(Collectors.toList());
// Alternativa A1
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

    public void tomarEventoSeleccionado(EventoSismico evento){
        bloquearEventoParaRevision(evento);
    }

    public void tomarOpcionCambioEstadoRechazado(EventoSismico evento){
        rechazarEventoSismico(evento);
    }

    public void tomarOpcionCambioEstadoConfirmado(EventoSismico evento) {
        confirmarEventoSismico(evento);
    }

    public void bloquearEventoParaRevision(EventoSismico evento) {
        LocalDate fechaHoraActual = LocalDate.now();
        evento.bloquearParaRevision(fechaHoraActual);
        //Paso 9
        buscarDatosSismicos(evento);
    }

    public Empleado buscarEmpleadoLogueado() {
        return sesion.getEmpleadoLogueado();
    }

    public LocalDate obtenerFechaHoraActual() {
        return LocalDate.now();
    }

    public void rechazarEventoSismico(EventoSismico evento) {
        if (validarDatos(evento)) {
            LocalDate fechaHoraActual = obtenerFechaHoraActual();
            Empleado responsable = buscarEmpleadoLogueado();
            evento.rechazar(fechaHoraActual, responsable);
        }
    }

    public void confirmarEventoSismico(EventoSismico evento) {
        if (validarDatos(evento)) {
            LocalDate fechaHoraActual = LocalDate.now();
            Empleado responsable = buscarEmpleadoLogueado();
            evento.confirmar(fechaHoraActual, responsable);
        }
    }

    public void buscarDatosSismicos (EventoSismico evento) {
        Map<String, Object> eventosMapeado = new HashMap<>();
        eventosMapeado.put("Alcance", evento.getAlcance());
        eventosMapeado.put("Clasificacion", evento.getClasificacion());
        eventosMapeado.put("Origen", evento.getOrigen());
        pantalla.mostrarDatosSismicos(eventosMapeado);
        //Paso 9.2
        buscarDatosSeriesTemporales(evento);
    }

    public void buscarDatosSeriesTemporales(EventoSismico evento) {
        Object [][] eventosClasificados = evento.buscarDatosSeriesTemp();
        //Paso 9.3
        llamarCUGenerarSismograma();
        //Paso 10
        habilitarMapa(evento);
    }
//paso 10
    public void habilitarMapa(EventoSismico evento) {
        pantalla.habilitarOpcionVerMapa();
        //paso 12
        habilitarOpcionModificarDatos(evento);
    }
//paso 12
    public void habilitarOpcionModificarDatos(EventoSismico evento) {
        pantalla.mostrarOpcionModificarDatos(evento);
        //paso 14
        habilitarOpcionCambioEstado();
    }
//paso 14
    public void habilitarOpcionCambioEstado(){
        pantalla.mostrarOpcionCambioEstado();
    }

    //paso 16
    public boolean validarDatos(EventoSismico evento) {
        return (evento.getMagnitud() != null && evento.getClasificacion() != null && evento.getOrigen() != null);
    }

    private void llamarCUGenerarSismograma () {
        pantalla.mostrarSismograma();

    }
}