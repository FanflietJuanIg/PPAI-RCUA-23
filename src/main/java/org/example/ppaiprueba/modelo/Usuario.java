package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer id;
    
    @NotNull
    @Column(name = "nombre", nullable = false, columnDefinition = "TEXT")
    private String nombreUsuario;
    
    @NotNull
    @Column(name = "contraseña", nullable = false, columnDefinition = "TEXT")
    private String contrasena;

    // Relación con empleado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado", nullable = false)
    private Empleado empleado;

    // Constructor por defecto requerido por JPA
    public Usuario() {}
    
    public Usuario(String nombreUsuario, String contrasena, Empleado empleado) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.empleado = empleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public boolean autenticar(String usuarioIngresado, String contrasenaIngresada) {
        return this.nombreUsuario.equals(usuarioIngresado) && this.contrasena.equals(contrasenaIngresada);
    }
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
