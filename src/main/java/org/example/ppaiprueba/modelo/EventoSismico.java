package org.example.ppaiprueba.modelo;

import org.example.ppaiprueba.State.AutoDetectado;
import org.example.ppaiprueba.State.Estado;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "EventoSismico")
public class EventoSismico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventoSismico")
    private Integer id;
    
    @Column(name = "fechaHoraFin")
    private LocalDateTime fechaHoraFin;
    
    @NotNull
    @Column(name = "fechaHoraOcurrencia", nullable = false)
    private LocalDateTime fechaHoraOcurrencia;
    
    @Column(name = "latitudEpicentro", precision = 10, scale = 6)
    private BigDecimal latitudEpicentro;
    
    @Column(name = "longitudEpicentro", precision = 10, scale = 6) 
    private BigDecimal longitudEpicentro;
    
    @Column(name = "latitudHipocentro", precision = 10, scale = 6)
    private BigDecimal latitudHipocentro;
    
    @Column(name = "longitudHipocentro", precision = 10, scale = 6)
    private BigDecimal longitudHipocentro;
    
    @Column(name = "valorMagnitud", precision = 4, scale = 2)
    private BigDecimal valorMagnitud;
    
    // Relaciones
    /*
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmpleadoAnalista", nullable = false)
    private Empleado empleadoAnalista;


     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idEstado", nullable = false)
    private Estado estadoActual;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idAlcanceSismo", nullable = false)
    private AlcanceSismo alcance;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idOrigenDeGeneracion", nullable = false)
    private OrigenDeGeneracion origen;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idMagnitudRitcher", nullable = false)
    private MagnitudRichter magnitud;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idClasificacionSismo", nullable = false)
    private ClasificacionSismo clasificacion;
    
    @OneToMany(mappedBy = "eventoSismico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SerieTemporal> seriesTemporales = new ArrayList<>();
    
    @OneToMany(mappedBy = "eventoSismico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public EventoSismico() {}
    
    public EventoSismico(MagnitudRichter magnitud, AlcanceSismo alcance, OrigenDeGeneracion origen, ClasificacionSismo clasificacion, LocalDateTime fechaHoraOcurrencia, Estado estadoInicial,
    String latitudEpicentro, String longitudEpicentro, String latitudHipocentro, String longitudHipocentro, List<SerieTemporal> seriesTemporales) {
        this.magnitud = magnitud;
        this.alcance = alcance;
        this.origen = origen;
        this.clasificacion = clasificacion;
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.estadoActual = estadoInicial;
        this.latitudEpicentro = latitudEpicentro != null ? new BigDecimal(latitudEpicentro) : null;
        this.longitudEpicentro = longitudEpicentro != null ? new BigDecimal(longitudEpicentro) : null;
        this.latitudHipocentro = latitudHipocentro != null ? new BigDecimal(latitudHipocentro) : null;
        this.longitudHipocentro = longitudHipocentro != null ? new BigDecimal(longitudHipocentro) : null;
        this.seriesTemporales = new ArrayList<>(seriesTemporales);
    }

    public void setMagnitud(String descripcion, Double valor) {
        this.magnitud.setDescripcionMagnitud(descripcion);
        this.magnitud.setNumero(java.math.BigDecimal.valueOf(valor));
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

    public String getClasificacion() {
        return clasificacion.getNombre();
    }

    public String getLatitudEpicentro(){
        return latitudEpicentro != null ? latitudEpicentro.toString() : null;
    }

    public String getLongitudEpicentro(){
        return longitudEpicentro != null ? longitudEpicentro.toString() : null;
    }

    public String getLatitudHipocentro(){
        return latitudHipocentro != null ? latitudHipocentro.toString() : null;
    }

    public String getLongitudHipocentro(){
        return longitudHipocentro != null ? longitudHipocentro.toString() : null;
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
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }
    
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    
    public void setFechaHoraOcurrencia(LocalDateTime fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }
    
    public BigDecimal getValorMagnitud() {
        return valorMagnitud;
    }
    
    public void setValorMagnitud(BigDecimal valorMagnitud) {
        this.valorMagnitud = valorMagnitud;
    }

    /*
    public Empleado getEmpleadoAnalista() {
        return empleadoAnalista;
    }
    
    public void setEmpleadoAnalista(Empleado empleadoAnalista) {
        this.empleadoAnalista = empleadoAnalista;
    }


     */
    public void setCambiosEstado(List<CambioEstado> cambiosEstado) {
        this.cambiosEstado = cambiosEstado;
    }

    public List<SerieTemporal> getSeriesTemporales(){
        return this.seriesTemporales;
    }

    public void setSeriesTemporales(List<SerieTemporal> seriesTemporales) {
        this.seriesTemporales = seriesTemporales;
    }
}
