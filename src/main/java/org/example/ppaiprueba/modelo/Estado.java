// ======================
// Estado.java
// ======================
package org.example.ppaiprueba.modelo;

/**
 * Clase que representa el estado de un elemento del sistema,
 * usando enums internos limitados para tipo y ámbito.
 */
public class Estado {
    public enum Tipo {
        PENDIENTE_REVISION,
        EN_REVISION,
        RECHAZADO,
        AUTODETECTADO,
        CONFIRMADO;
    }

    public enum Ambito {
        EVENTO_SISMICO,
        SISMOGRAFO;
    }

    private Tipo tipo;
    private Ambito ambito;

    /**
     * Constructor completo para estado con ámbito y flag de autodetección.
     */
    public Estado(Tipo tipo, Ambito ambito) {
        this.tipo = tipo;
        this.ambito = ambito;
    }

    /**
     * Constructor básico para estados de eventos sísmicos no autodetectados.
     */
    public Estado(Tipo tipo) {
        this(tipo, Ambito.EVENTO_SISMICO);
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Ambito getAmbito() {
        return ambito;
    }

    /**
     * Indica si el estado corresponde a un evento autodetectado.
     */
    public boolean esAutodetectado() {
        return tipo == tipo.AUTODETECTADO;
    }

    public String getNombre() {
        return tipo.name().replace('_', ' ');
    }

    public boolean esPendienteRevision() {
        return tipo == Tipo.PENDIENTE_REVISION;
    }

    public boolean esEnRevision() {
        return tipo == Tipo.EN_REVISION;
    }

    public boolean esRechazado() {
        return tipo == Tipo.RECHAZADO;
    }

    public boolean esConfirmado() {
        return tipo == Tipo.CONFIRMADO;
    }

    public boolean esAmbitoEventoSismico(){
        return ambito == Ambito.EVENTO_SISMICO;
    }
}
