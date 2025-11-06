package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "ClasificacionSismo")
public class ClasificacionSismo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClasificacionSismo")
    private Integer id;
    
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "kmProfundidadDesde", precision = 8, scale = 3)
    private BigDecimal kmProfundidadDesde;
    
    @Column(name = "kmProfundidadHasta", precision = 8, scale = 3)
    private BigDecimal kmProfundidadHasta;

    // Constructor por defecto requerido por JPA
    public ClasificacionSismo() {}

    public ClasificacionSismo(double desde, double hasta, String nombre) {
        this.kmProfundidadDesde = BigDecimal.valueOf(desde);
        this.kmProfundidadHasta = BigDecimal.valueOf(hasta);
        this.nombre = nombre;
    }

    public BigDecimal getKmProfundidadDesde() {
        return kmProfundidadDesde;
    }

    public BigDecimal getKmProfundidadHasta() {
        return kmProfundidadHasta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setKmProfundidadDesde(BigDecimal desde) {
        this.kmProfundidadDesde = desde;
    }

    public void setKmProfundidadHasta(BigDecimal hasta) {
        this.kmProfundidadHasta = hasta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
}
