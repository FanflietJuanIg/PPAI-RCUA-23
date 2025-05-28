//Controlador que implementa la logica de negocio CUController.java
package org.example.ppaiprueba.control;

import org.example.ppaiprueba.modelo.Empleado;
import org.example.ppaiprueba.modelo.EventoSismico;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.example.ppaiprueba.modelo.Sesion;

import org.example.ppaiprueba.modelo.Estado;
import org.example.ppaiprueba.vista.RegistrarRevisionFController;

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
        pantalla.mostrarEventos(eventosOrdenados);
    }


    public void tomarFechaHoraActual(EventoSismico evento, boolean band){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        if (band){buscarEstadoRechazado(evento, fechaHoraActual);}
        else {buscarEstadoEnRevision(evento, fechaHoraActual);}
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

    public void bloquearEventoParaRevision(Estado enRevision, EventoSismico evento, LocalDateTime fechaHoraActual){
            evento.bloquearParaRevision(enRevision, fechaHoraActual);
            buscarDatosSismicos(evento);
    }

    public void buscarEmpleadoLogeado(EventoSismico evento, LocalDateTime fechaHoraActual ,Estado rechazado){
        Empleado empleadoLogueado = sesion.getEmpleadoLogueado();
        rechazarEventoSismico(rechazado, empleadoLogueado, evento, fechaHoraActual);
    }
    public void rechazarEventoSismico(Estado estadoRechazado,Empleado empleadoLogueado, EventoSismico evento, LocalDateTime fechaHoraActual) {
        evento.rechazar(estadoRechazado, empleadoLogueado, fechaHoraActual);
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
        //TODO: simular SISMOGRAMA
        habilitarMapa();

    }

    public void habilitarMapa(){
        pantalla.habilitarOpcionVerMapa();
        habilitarOpcionModificarDatos();
    }

    public void habilitarOpcionModificarDatos(){
        pantalla.habilitarOpcionModificarDatos();
    }

    /*
    public void habilitarMapa() {
        pantalla.habilitarOpcionVerMapa()


    }
*/
    public void validarDatos(EventoSismico evento) {
        if (evento.getMagnitud() != null && evento.getClasificacion() != null && evento.getOrigen() != null) {
            tomarFechaHoraActual(evento, true);

        }
    }

// TODO: Preguntar como se valida la opcion que tomo el AS
}
