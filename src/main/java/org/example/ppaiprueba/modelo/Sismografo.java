package org.example.ppaiprueba.modelo;

import org.example.ppaiprueba.State.Estado;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Sismografo")
public class Sismografo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSismografo")
    private Integer id;
    
    @Column(name = "fechaAdquisicion")
    private LocalDate fechaAdquisicion;
    
    @Column(name = "nroSerie", columnDefinition = "TEXT")
    private String nroSerie;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idEstado", nullable = true)
    private Estado estado;
    
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "idEstacionSismologica", nullable = false)
    private EstacionSismologica estacionSismologica;

    // Constructor por defecto requerido por JPA
    public Sismografo() {}
    
    public Sismografo(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    public String getIdEstacionSismologica() {
        return estacionSismologica.getCodigoEstacion();
    }
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }
    
    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
    
    public String getNroSerie() {
        return nroSerie;
    }
    
    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }
    
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }
    
    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }
}