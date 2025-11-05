// ======================
// Estado.java
// ======================
package org.example.ppaiprueba.State;

import org.example.ppaiprueba.modelo.CambioEstado;
import org.example.ppaiprueba.modelo.Empleado;
import org.example.ppaiprueba.modelo.EventoSismico;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que representa el estado de un elemento del sistema,
 * usando enums internos limitados para tipo y ámbito.
 */
public abstract class Estado {

    protected String nombre;


    public Estado(String nombre) {
        this.nombre = nombre;
    }

    protected Estado() {
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
}
