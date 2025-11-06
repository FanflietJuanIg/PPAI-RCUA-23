package org.example.ppaiprueba.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Empleado")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private Integer id;
    
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @NotNull
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;
    
    @Column(name = "mail", length = 150)
    private String mail;
    
    @Column(name = "telefono", length = 50)
    private String telefono;

    // Nota: Por simplicidad, mantenemos rol como String por ahora
    // En una implementación completa sería una FK a la tabla Rol
    private String rol;

    // Constructor por defecto requerido por JPA
    public Empleado() {}
    
    public Empleado(String nombre, String apellido, String mail, String telefono, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String obtenerMail() {
        return this.mail;
    }
    
    // Setters adicionales para JPA
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
}
