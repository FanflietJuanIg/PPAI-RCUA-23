package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MuestraSismica")
public class MuestraSismica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMuestraSismica")
    private Integer id;
    
    @NotNull
    @Column(name = "fechaHoraMuestra", nullable = false)
    private LocalDateTime fechaHoraMuestra;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSerieTemporal", nullable = false)
    private SerieTemporal serieTemporal;
    
    @OneToMany(mappedBy = "muestraSismica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleMuestraSismica> detalleMuestraSismica = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public MuestraSismica() {}
    
    public MuestraSismica(LocalDateTime fechaHoraMuestra, List<DetalleMuestraSismica> detalleMuestraSismica){
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detalleMuestraSismica = new ArrayList<>(detalleMuestraSismica);
    }


    public Object[] getDatos() {
        Object[] vectorDatos = new Object[2];
        vectorDatos[0] = fechaHoraMuestra.toString();
        vectorDatos[1] = obtenerDetalles();
        return vectorDatos;
    }
    public String[][] obtenerDetalles(){
        String[][] datosDetalles = new String[detalleMuestraSismica.size()][2];
        for (int i = 0; i < detalleMuestraSismica.size();i++){
            DetalleMuestraSismica detalle = detalleMuestraSismica.get(i);
            String[] datosTemp = detalle.getDatos();
            datosDetalles[i][0] = datosTemp[0];
            datosDetalles[i][1] = datosTemp[1];
        }
        return datosDetalles;
    }
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDateTime getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }
    
    public void setFechaHoraMuestra(LocalDateTime fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }
    
    public SerieTemporal getSerieTemporal() {
        return serieTemporal;
    }
    
    public void setSerieTemporal(SerieTemporal serieTemporal) {
        this.serieTemporal = serieTemporal;
    }
    
    public List<DetalleMuestraSismica> getDetalleMuestraSismica() {
        return detalleMuestraSismica;
    }
    
    public void setDetalleMuestraSismica(List<DetalleMuestraSismica> detalleMuestraSismica) {
        this.detalleMuestraSismica = detalleMuestraSismica;
    }
}