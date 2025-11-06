package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "MagnitudRitcher")
public class MagnitudRichter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMagnitudRitcher")
    private Integer id;
    
    @Column(name = "descripcionMagnitud", length = 200)
    private String descripcionMagnitud;
    
    @Column(name = "numero", precision = 6, scale = 2)
    private BigDecimal numero;

    // Constructor por defecto requerido por JPA
    public MagnitudRichter() {}
    
    public MagnitudRichter(String descripcionMagnitud, Double numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero != null ? BigDecimal.valueOf(numero) : null;
    }

    public String getDescripcionMagnitud() {
        return descripcionMagnitud;
    }

    public BigDecimal getNumero() {
        return numero;
    }

    public void setDescripcionMagnitud(String descripcionMagnitud) {
        this.descripcionMagnitud = descripcionMagnitud;
    }

    public void setNumero(BigDecimal numero) {
        this.numero = numero;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
}
