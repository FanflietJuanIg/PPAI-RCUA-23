package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.example.ppaiprueba.modelo.Estado;

public class EventoSismico {
    private MagnitudRichter magnitud;
    private AlcanceSismo alcance;
    private OrigenDeGeneracion origen;
    private ClasificacionSismo clasificacion;
    private LocalDateTime fechaHoraOcurrencia;
    private Estado estadoActual;
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    public EventoSismico(MagnitudRichter magnitud, AlcanceSismo alcance, OrigenDeGeneracion origen, ClasificacionSismo clasificacion, LocalDateTime fechaHoraOcurrencia, Estado estadoInicial) {
        this.magnitud = magnitud;
        this.alcance = alcance;
        this.origen = origen;
        this.clasificacion = clasificacion;
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.estadoActual = estadoInicial;
    }
// get y set a otros objetos cambiarlos
    /*
    // Getters y Setters
    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    // Esto va en Cambio de Estado =)
    /*public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(LocalDateTime fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getResponsableRevision() {
        return responsableRevision;
    }

    public void setResponsableRevision(String responsableRevision) {
        this.responsableRevision = responsableRevision;
    }
*/


    // metodos
    public boolean esAutodetectado() {
        return estadoActual.esAutodetectado();
    }

    public boolean esPendienteRevision() {
        return estadoActual.esPendienteRevision();
    }


    public void bloquearParaRevision() {
        // lógica para marcar que el evento está siendo revisado
        System.out.println("Evento bloqueado para revisión");
    }

    public void agregarCambioEstado(CambioEstado cambio) {
        for (CambioEstado ce : cambiosEstado) {
            if (ce.esActual()) {
                ce.setFechaHoraFin(LocalDateTime.now());
            }
        }
    }
}

/*
    public boolean estaEnRevision() {
        return estadoActual.getNombre().equalsIgnoreCase("En Revision");
    }

    public boolean estaRechazado() {
        return estadoActual.getNombre().equalsIgnoreCase("Rechazado");
    }


        }
        cambiosEstado.add(cambio);
        this.estadoActual = cambio.getEstado();
    }
*/



/*



    @Override
    public String toString() {
        return "Magnitud " + magnitud + " - Origen: " + origen;
    }

    // Datos simulados
    public static List<EventoSismico> generarEventosSimulados() {
        List<EventoSismico> lista = new ArrayList<>();
        lista.add(new EventoSismico(5.0, "Alta", "Tectónico", "Pendiente",LocalDateTime.now().minusHours(3), pendiente));
        lista.add(new EventoSismico(4.3, "Media", "Volcánico", "Pendiente",LocalDateTime.now().minusHours(6), pendiente));
        lista.add(new EventoSismico(3.2, "Local", "Argentina", "Confirmado")); // Este no aparecerá
        return lista;
    }
}
*/