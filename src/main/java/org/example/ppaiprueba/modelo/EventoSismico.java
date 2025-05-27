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
/*
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
// corregir
*/
public String[][] buscarDatosSeriesTemp() {
    String[][] datosSeries = new String[seriesTemporales.size()][2];

    for (int i = 0; i < seriesTemporales.size(); i++) {
        SerieTemporal serie = seriesTemporales.get(i);
        Object[][] datosTemp = serie.getDatos(); // se asume que devuelve Object[][]

        // Convertimos manualmente a String (si estás seguro del tipo)
        datosSeries[i][0] = datosTemp[0][0].toString();
        datosSeries[i][1] = datosTemp[0][1].toString();
    }

    return clasificarDatosPorEstacion(datosSeries);
}

    public String[][] clasificarDatosPorEstacion(String[][] datosSeries){

        String[][] copia = Arrays.stream(datosSeries)
                .map(String[]::clone)
                .toArray(String[][]::new);

        // Ordenar por primera columna (índice 0)
        Arrays.sort(copia, Comparator.comparing(fila -> fila[0]));

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

    public void bloquearParaRevision(Estado estado, LocalDateTime fechaHoraActual){
        Empleado empleadoCambio = null;
        for (CambioEstado ce : cambiosEstado) {
            if (ce.esActual()) {
                ce.setFechaHoraFin(fechaHoraActual);
                empleadoCambio = ce.getResponsable();
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



}

/*
    //Registra un cambio de estado en el evento.

    public void agregarCambioEstado(CambioEstado cambio) {
        for (CambioEstado ce : cambiosEstado) {
            if (ce.esActual()) {
                ce.setFechaHoraFin(LocalDateTime.now());
            }
        }
        cambiosEstado.add(cambio);
        this.estadoActual = cambio.getEstado();
    }

     //Registra un cambio de estado en el evento.

public void agregarCambioEstado(CambioEstado cambio) {
    for (CambioEstado ce : cambiosEstado) {
        if (ce.esActual()) {
            ce.setFechaHoraFin(LocalDateTime.now());
        }
    }
    cambiosEstado.add(cambio);
    this.estadoActual = cambio.getEstado();
}

    public void agregarCambioEstado(CambioEstado cambio) {
        cerrarEstadoActual(LocalDateTime.now());
        cambiosEstado.add(cambio);
    }






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