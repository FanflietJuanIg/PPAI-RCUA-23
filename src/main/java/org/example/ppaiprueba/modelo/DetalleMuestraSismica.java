package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "DetalleMuestraSismica")
public class DetalleMuestraSismica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleMuestraSismica")
    private Integer id;
    
    @NotNull
    @Column(name = "valor", nullable = false, precision = 10, scale = 4)
    private BigDecimal valor;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idMuestraSismica", nullable = false)
    private MuestraSismica muestraSismica;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idTipoDeDato", nullable = false)
    private TipoDeDato tipoDeDato;

    // Constructor por defecto requerido por JPA
    public DetalleMuestraSismica() {}
    
    public DetalleMuestraSismica(double valor, TipoDeDato tipoDeDato){
        this.valor = BigDecimal.valueOf(valor);
        this.tipoDeDato = tipoDeDato;
    }
    public String[] getDatos(){
        String[] vectorDatos = new String[2];
        vectorDatos[0] = valor.toString();
        vectorDatos[1] = tipoDeDato.getDenominacion();
        return vectorDatos;
    }
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public BigDecimal getValor() {
        return valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    public MuestraSismica getMuestraSismica() {
        return muestraSismica;
    }
    
    public void setMuestraSismica(MuestraSismica muestraSismica) {
        this.muestraSismica = muestraSismica;
    }
    
    public TipoDeDato getTipoDeDato() {
        return tipoDeDato;
    }
    
    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }
}
