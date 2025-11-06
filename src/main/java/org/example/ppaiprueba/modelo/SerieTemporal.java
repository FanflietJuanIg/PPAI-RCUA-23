package org.example.ppaiprueba.modelo;

import org.example.ppaiprueba.State.Estado;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SerieTemporal")
public class SerieTemporal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSerieTemporal")
    private Integer id;
    
    @Column(name = "condicionAlarma", columnDefinition = "TEXT")
    private String condicionAlarma;
    
    @Column(name = "fechaHoraInicioRegistroMuestras")
    private LocalDateTime fechaHoraInicioRegistroMuestras;
    
    @Column(name = "fechaHoraRegistro")
    private LocalDateTime fechaHoraRegistro;
    
    @Column(name = "frecuenciaMuestreo", precision = 6, scale = 2)
    private BigDecimal frecuenciaMuestreo;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSismografo", nullable = false)
    private Sismografo sismografo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstado", nullable = false)
    private Estado estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEventoSismico", nullable = false)
    private EventoSismico eventoSismico;
    
    @OneToMany(mappedBy = "serieTemporal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MuestraSismica> muestraSismicas = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public SerieTemporal() {}
    
    public SerieTemporal(String condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestras, LocalDateTime fechaHoraRegistro, double frecuenciaMuestreo, List<MuestraSismica> muestraSismicas, Sismografo sismografo) {
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.condicionAlarma = condicionAlarma;
        this.frecuenciaMuestreo = BigDecimal.valueOf(frecuenciaMuestreo);
        this.muestraSismicas = new ArrayList<>(muestraSismicas);
        this.sismografo = sismografo;
    }
    public Object[][] getDatos(){
        Object[] vectorDatos = new Object[6];
        vectorDatos[0] = condicionAlarma;
        vectorDatos[1] = fechaHoraInicioRegistroMuestras.toString();
        vectorDatos[2] =  fechaHoraRegistro.toString();
        vectorDatos[3] = String.valueOf(frecuenciaMuestreo);
        vectorDatos[4] = obtenerMuestras();

        Object[][] vectorID = new Object[1][2];
        vectorID[0][0] = sismografo.getIdEstacionSismologica();
        vectorID[0][1] = vectorDatos;

        return vectorID;
    }
    public Object[] obtenerMuestras(){
        Object[] datosMuestras = new Object[muestraSismicas.size()];
        for (int i = 0; i < muestraSismicas.size(); i++){
            MuestraSismica muestra = muestraSismicas.get(i);
            datosMuestras[i] = muestra.getDatos();
            System.out.println("DatosMuestras: " + datosMuestras[i]);
        }
        return datosMuestras;
    }
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCondicionAlarma() {
        return condicionAlarma;
    }
    
    public void setCondicionAlarma(String condicionAlarma) {
        this.condicionAlarma = condicionAlarma;
    }
    
    public LocalDateTime getFechaHoraInicioRegistroMuestras() {
        return fechaHoraInicioRegistroMuestras;
    }
    
    public void setFechaHoraInicioRegistroMuestras(LocalDateTime fechaHoraInicioRegistroMuestras) {
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
    }
    
    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }
    
    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }
    
    public BigDecimal getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }
    
    public void setFrecuenciaMuestreo(BigDecimal frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }
    
    public Sismografo getSismografo() {
        return sismografo;
    }
    
    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }
    
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public EventoSismico getEventoSismico() {
        return eventoSismico;
    }
    
    public void setEventoSismico(EventoSismico eventoSismico) {
        this.eventoSismico = eventoSismico;
    }
    
    public List<MuestraSismica> getMuestraSismicas() {
        return muestraSismicas;
    }
    
    public void setMuestraSismicas(List<MuestraSismica> muestraSismicas) {
        this.muestraSismicas = muestraSismicas;
    }
}
