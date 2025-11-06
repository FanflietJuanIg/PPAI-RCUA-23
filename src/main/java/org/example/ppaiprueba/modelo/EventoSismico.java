package org.example.ppaiprueba.modelo;

import org.example.ppaiprueba.State.AutoDetectado;
import org.example.ppaiprueba.State.Estado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    public void setMagnitud(String descripcion, Double valor) {
        this.magnitud.setDescripcionMagnitud(descripcion);
        this.magnitud.setNumero(valor);
    }

    public Object[] getMagnitud(){
        Object[] vector = new Object[2];
        vector[0] = magnitud.getDescripcionMagnitud();
        vector[1] = magnitud.getNumero();
        return vector;
    }

    public void setAlcance(String descripcion, String nombre) {
        this.alcance.setDescripcion(descripcion);
        this.alcance.setNombre(nombre);
    }

    public String getAlcance(){
        return alcance.getNombre();
    }

    public void setOrigen(String descripcion, String nombre) {
        this.alcance.setDescripcion(descripcion);
        this.alcance.setNombre(nombre);
    }

    public String getOrigen(){
        return origen.getNombre();
    }

    public ClasificacionSismo.Nombre getClasificacion() {
        return clasificacion.getNombre();

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
        return estadoActual instanceof AutoDetectado;
        //return estadoActual.esAutodetectado();
    }

    /*
    public boolean esPendienteRevision() {
        return estadoActual.esPendienteRevision();
    }
    */


public Object[][] buscarDatosSeriesTemp() {

    Object[][] datosSeries = new Object[seriesTemporales.size()][2];
    for (int i = 0; i < seriesTemporales.size(); i++) {
        SerieTemporal serie = seriesTemporales.get(i);
        Object[][] datosTemp = serie.getDatos();
        datosSeries[i][0] = datosTemp[0][0];
        datosSeries[i][1] = datosTemp[0][1];

    }
    return clasificarDatosPorEstacion(datosSeries);
}

    // Termina paso 9.2
    public Object[][] clasificarDatosPorEstacion(Object[][] datosSeries) {
        Object[][] copia = Arrays.stream(datosSeries)
                .map(Object[]::clone)
                .toArray(Object[][]::new);

        Arrays.sort(copia, (fila1, fila2) ->
                fila1[0].toString().compareTo(fila2[0].toString())
        );

        return copia;
    }

    public void bloquearParaRevision(LocalDate fechaActual) {
        estadoActual.bloquearParaRevision(fechaActual, this);
    }

    public void rechazar(LocalDate fechaActual, Empleado responsable) {
        estadoActual.rechazar(fechaActual, responsable, this);
    }

    public void confirmar(LocalDate fechaActual, Empleado responsable) {
        estadoActual.confirmar(fechaActual, responsable, this);
    }

    public List<CambioEstado> getCambiosEstado() {
        return cambiosEstado;
    }

    public void addCambioEstado(CambioEstado ca) {
        cambiosEstado.add(ca);
    }
}
