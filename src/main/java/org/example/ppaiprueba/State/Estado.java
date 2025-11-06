// ======================
// Estado.java
// ======================
package org.example.ppaiprueba.State;

import org.example.ppaiprueba.modelo.CambioEstado;
import org.example.ppaiprueba.modelo.Empleado;
import org.example.ppaiprueba.modelo.EventoSismico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Clase que representa el estado de un elemento del sistema,
 * usando enums internos limitados para tipo y ámbito.
 */
@Entity
@Table(name = "Estado")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstado")
    protected Integer id;
    
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    protected String nombre;
    
    @Column(name = "ambito", length = 100)
    protected String ambito;


    // Constructor por defecto requerido por JPA
    protected Estado() {}
    
    public Estado(String nombre) {
        this.nombre = nombre;
    }


    public boolean esAutodetectado() {
        return nombre.equalsIgnoreCase("autodetectado");
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esPendienteRevision() {
        return nombre.equalsIgnoreCase("Pendiente Revision");
    }

    public boolean esEnRevision() {
        return nombre.equalsIgnoreCase("bloqueado para revision");
    }

    public boolean esRechazado() {
        return nombre.equalsIgnoreCase("rechazado");
    }

    public boolean esConfirmado() {
        return nombre.equalsIgnoreCase("confirmado");
    }

    public void bloquearParaRevision(LocalDate fecha, EventoSismico evento) {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }
    public void rechazar(LocalDate fecha, Empleado responsable, EventoSismico evento) {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }
    public void registraPendienteDeCierre() {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }
    public void anular() {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }
    public void cerrar() {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }

    public void confirmar(LocalDate fecha, Empleado responsable, EventoSismico evento) {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }

    public void derivar() {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }

    public void controlarTiempo() {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }

    public void registrarPendienteRevision() {
        throw new UnsupportedOperationException(
                "Operacion Invalida en " + this.getClass().getSimpleName()

        );
    }
    
    // Getters y Setters JPA
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getAmbito() {
        return ambito;
    }
    
    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
}
