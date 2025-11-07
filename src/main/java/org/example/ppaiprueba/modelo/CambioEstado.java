package org.example.ppaiprueba.modelo;

import org.example.ppaiprueba.State.Estado;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CambioEstado")
public class CambioEstado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCambioEstado")
    private Integer id;
    
    @Column(name = "fechaHoraInicio")
    private LocalDateTime fechaHoraInicio;
    
    @Column(name = "fechaHoraFin")
    private LocalDateTime fechaHoraFin;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "idEstado", nullable = false)
    private Estado estado;
    
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmpleado", nullable = false)
    private Empleado empleado;
    
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "idEventoSismico", nullable = false)
    private EventoSismico eventoSismico;

    // Constructor por defecto requerido por JPA
    public CambioEstado() {}
    
    public CambioEstado(LocalDate inicio, Estado estado, Empleado empleado) {
        this.fechaHoraInicio = inicio.atStartOfDay(); // Convertir LocalDate a LocalDateTime
        this.estado = estado;
        this.empleado = empleado;
    }

    public boolean esActual() {
        return fechaHoraFin == null;
    }

    public void setFechaHoraFin(LocalDate fin) {
        this.fechaHoraFin = fin.atStartOfDay(); // Convertir LocalDate a LocalDateTime
    }

    public Estado getEstado() {
        return estado;
    }

    public void setResponsable(Empleado empleado) {this.empleado = empleado;}

    public Empleado getResponsable() {return empleado;}
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }
    
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }
    
    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }
    
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public EventoSismico getEventoSismico() {
        return eventoSismico;
    }
    
    public void setEventoSismico(EventoSismico eventoSismico) {
        this.eventoSismico = eventoSismico;
    }
}
