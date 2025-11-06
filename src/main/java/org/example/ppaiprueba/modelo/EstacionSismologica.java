package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EstacionSismologica")
public class EstacionSismologica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstacionSismologica")
    private Integer id;
    
    @NotNull
    @Column(name = "codigoEstacion", nullable = false, length = 100)
    private String codigoEstacion;
    
    @Column(name = "documentoCertificacionAdq", length = 200)
    private String documentoCertificacionAdq;
    
    @Column(name = "fechaSolicitudCertificacion")
    private LocalDate fechaSolicitudCertificacion;
    
    @Column(name = "latitud", precision = 10, scale = 6)
    private BigDecimal latitud;
    
    @Column(name = "longitud", precision = 10, scale = 6)
    private BigDecimal longitud;
    
    @Column(name = "nombre", length = 200)
    private String nombre;
    
    @Column(name = "nroCertificacionAdquisicion", length = 200)
    private String nroCertificacionAdquisicion;

    // Constructor por defecto requerido por JPA
    public EstacionSismologica() {}
    
    public EstacionSismologica(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public String getCodigoEstacion() {
        return codigoEstacion;
    }
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setCodigoEstacion(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }
    
    public String getDocumentoCertificacionAdq() {
        return documentoCertificacionAdq;
    }
    
    public void setDocumentoCertificacionAdq(String documentoCertificacionAdq) {
        this.documentoCertificacionAdq = documentoCertificacionAdq;
    }
    
    public LocalDate getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }
    
    public void setFechaSolicitudCertificacion(LocalDate fechaSolicitudCertificacion) {
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
    }
    
    public BigDecimal getLatitud() {
        return latitud;
    }
    
    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }
    
    public BigDecimal getLongitud() {
        return longitud;
    }
    
    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }
    
    public void setNroCertificacionAdquisicion(String nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }
}
