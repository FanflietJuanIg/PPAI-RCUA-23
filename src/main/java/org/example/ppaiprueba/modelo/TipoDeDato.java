package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "TipoDeDato")
public class TipoDeDato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoDeDato")
    private Integer id;
    
    @Column(name = "denominacion", columnDefinition = "TEXT")
    private String denominacion;
    
    @Column(name = "nombreTipoDato", columnDefinition = "TEXT")
    private String nombreTipoDato;
    
    @Column(name = "unidadMedida", columnDefinition = "TEXT")
    private String unidadMedida;

    // Constructor por defecto requerido por JPA
    public TipoDeDato() {}
    
    public TipoDeDato(String denominacion){
        this.denominacion = denominacion;
    }

    public String getDenominacion(){
        return denominacion;
    }
    
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    
    public String getNombreTipoDato() {
        return nombreTipoDato;
    }
    
    public void setNombreTipoDato(String nombreTipoDato) {
        this.nombreTipoDato = nombreTipoDato;
    }
    
    public String getUnidadMedida() {
        return unidadMedida;
    }
    
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
}
