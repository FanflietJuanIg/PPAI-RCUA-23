package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import javafx.util.Pair;
import org.example.ppaiprueba.modelo.Usuario;

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

    // Intento hacer un set de magnitud
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

    //TODO: tecnicamente segun el diagrama de secuancia seria solo get nombre
    public Object[] getAlcance(){
        Object[] vector = new Object[2];
        vector[0] = alcance.getDescripcion();
        vector[1] = alcance.getNombre();
        return vector;
    }

    public void setOrigen(String descripcion, String nombre) {
        this.alcance.setDescripcion(descripcion);
        this.alcance.setNombre(nombre);
    }

    public Object[] getOrigen(){
        Object[] vector = new Object[2];
        vector[0] = origen.getDescripcion();
        vector[1] = origen.getNombre();
        return vector;
    }

    public String getClasificacion() {
        return origen.getNombre();

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
    return clasificarDatosPorEstacion(datosSeries);
}

    //FIXME: cambios por ai, verificar si funcion
    public Object[][] clasificarDatosPorEstacion(Object[][] datosSeries) {
        Object[][] copia = Arrays.stream(datosSeries)
                .map(Object[]::clone)
                .toArray(Object[][]::new);

        Arrays.sort(copia, (fila1, fila2) ->
                fila1[0].toString().compareTo(fila2[0].toString())
        );

        return copia;
    }

    public void bloquearParaRevision() {
        // lógica para marcar que el evento está siendo revisado
        System.out.println("Evento bloqueado para revisión");
    }

//cambiar el nombre del metodo en el diagrama de secuencia
    public void newCambioEstado(LocalDateTime fechaHoraActual,Estado estado, Empleado empleado) {
        CambioEstado cambio = new CambioEstado(fechaHoraActual, estado, empleado);
        // TODO: //la siguiente parte no especifica en el diagrama de secuencia, talvez conviene un self de evento sismico que setee su estado actual y añada su cambio de estado
        cambiosEstado.add(cambio);
        this.estadoActual = cambio.getEstado();
    }
// TODO: se deberia pasar como parametro el empleado logueado??
    public void bloquearParaRevision(Estado estado, LocalDateTime fechaHoraActual){
        Empleado empleadoCambio = null;
        for (CambioEstado ce : cambiosEstado) {
            if (ce.esActual()) {
                ce.setFechaHoraFin(fechaHoraActual);
                break;
            }
        }
        newCambioEstado(fechaHoraActual,estado, empleadoCambio);
    }
    public void rechazar(Estado estado,Empleado empleadoLogueado, LocalDateTime fechaHoraActual) {
        for (CambioEstado ce : cambiosEstado) {
            if (ce.esActual()) {
                ce.setFechaHoraFin(fechaHoraActual);
                break;
            }
        }
        newCambioEstado(fechaHoraActual,estado, empleadoLogueado);
    }

    public void getUbicacion() {

    }


}
