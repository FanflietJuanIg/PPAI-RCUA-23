package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import javafx.util.Pair;

public class EventoSismico {
    private MagnitudRichter magnitud;
    private AlcanceSismo alcance;
    private OrigenDeGeneracion origen;
    private ClasificacionSismo clasificacion;
    private LocalDateTime fechaHoraOcurrencia;
    private Estado estadoActual;
    private String latitudEpicentro;
    private String longitudEpicentro;
    private String latitudHipocentro;
    private String longitudHipocentro;
    private List<SerieTemporal> seriesTemporales;
    //private Empleado empleado;
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    public EventoSismico(MagnitudRichter magnitud, AlcanceSismo alcance, OrigenDeGeneracion origen, ClasificacionSismo clasificacion, LocalDateTime fechaHoraOcurrencia, Estado estadoInicial,
    String latitudEpicentro, String longitudEpicentro, String latitudHipocentro, String longitudHipocentro, List<SerieTemporal> seriesTemporales) {
        this.magnitud = magnitud;
        this.alcance = alcance;
        this.origen = origen;
        this.clasificacion = clasificacion;
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.estadoActual = estadoInicial;
        this.latitudEpicentro = latitudEpicentro;
        this.longitudEpicentro = longitudEpicentro;
        this.latitudHipocentro = latitudHipocentro;
        this.longitudHipocentro = longitudHipocentro;
        this.seriesTemporales = new ArrayList<>(seriesTemporales);
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
    public Object[] obtenerMagnitud(){
        Object[] vector = new Object[2];
        vector[0] = magnitud.getDescripcionMagnitud();
        vector[1] = magnitud.getNumero();
        return vector;
    }
    public String getLatitudEpicentro(){
        return latitudEpicentro;
    }

    public String getLongitudEpicentro(){
        return  longitudEpicentro;
    }

    public String getLatitudHipocentro(){
        return  latitudHipocentro;
    }

    public String getLongitudHipocentro(){
        return  longitudHipocentro;
    }

    public void setEstadoActual(Estado estadoActual){
        this.estadoActual = estadoActual;
    }

    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    // metodos
    public boolean esAutodetectado() {
        return estadoActual.esAutodetectado();
    }

    public boolean esPendienteRevision() {
        return estadoActual.esPendienteRevision();
    }

    public Object[][] buscarDatosSeriesTemp() {

    Object[][] datosSeries = new String[seriesTemporales.size()][2];
    for (int i = 0; i < seriesTemporales.size(); i++) {
        SerieTemporal serie = seriesTemporales.get(i);
        Object[][] datosTemp = serie.getDatos();
        datosSeries[i][0] = datosTemp[0][0];
        datosSeries[i][1] = datosTemp[0][1];

        }
    return clasificaPorEstacion(datosSeries);
}

public Object[][] clasificaPorEstacion(Object[][] datosSeries) {
    Object[][] copia = Arrays.stream(datosSeries)
            .map(Object[]::clone)
            .toArray(Object[][]::new);
            
    Arrays.sort(copia, Comparator.comparing(fila -> fila[0].toString()));
    
    return copia;
}

    public void bloquearParaRevision() {
        // lógica para marcar que el evento está siendo revisado
        System.out.println("Evento bloqueado para revisión");
    }

    public void agregarCambioEstado(CambioEstado cambio) {
        cerrarEstadoActual(LocalDateTime.now());
        cambiosEstado.add(cambio);
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