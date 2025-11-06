package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Suscripcion") // Usando la tabla Suscripcion del esquema
public class Sesion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSuscripcion")
    private Integer id;
    
    @Column(name = "fechaHoraInicioSuscripcion")
    private LocalDateTime fechaHoraInicioSuscripcion;
    
    @Column(name = "fechaHoraInicioCancelacion")
    private LocalDateTime fechaHoraInicioCancelacion;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuarioAutenticado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstacionSismologica")
    private EstacionSismologica estacionSismologica;

    // Constructor por defecto requerido por JPA
    public Sesion() {}
    
    public Sesion(Usuario usuario) {
        this.usuarioAutenticado = usuario;
        this.fechaHoraInicioSuscripcion = LocalDateTime.now();
    }

    public Usuario getUsuario() {
        return usuarioAutenticado;
    }

    public Empleado getEmpleadoLogueado() {
        return usuarioAutenticado.getEmpleado();
    }
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDateTime getFechaHoraInicioSuscripcion() {
        return fechaHoraInicioSuscripcion;
    }
    
    public void setFechaHoraInicioSuscripcion(LocalDateTime fechaHoraInicioSuscripcion) {
        this.fechaHoraInicioSuscripcion = fechaHoraInicioSuscripcion;
    }
    
    public LocalDateTime getFechaHoraInicioCancelacion() {
        return fechaHoraInicioCancelacion;
    }
    
    public void setFechaHoraInicioCancelacion(LocalDateTime fechaHoraInicioCancelacion) {
        this.fechaHoraInicioCancelacion = fechaHoraInicioCancelacion;
    }
    
    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
    
    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }
    
    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }
    
    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }
}
