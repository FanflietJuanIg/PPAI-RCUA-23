package org.example.ppaiprueba.modelo;

import java.util.ArrayList;
import java.util.List;

public class Estado {
    private static final List<Estado> ESTADOS_POSIBLES = new ArrayList<>();
    public static final Estado PENDIENTE = new Estado("Pendiente");
    public static final Estado CONFIRMADO = new Estado("Confirmado");
    public static final Estado RECHAZADO = new Estado("Rechazado");
    public static final Estado DERIVADO = new Estado("Derivado");
    public static final Estado BLOQUEADO_EN_REVISION = new Estado("Bloqueado en revisión");

    static {
        ESTADOS_POSIBLES.add(PENDIENTE);
        ESTADOS_POSIBLES.add(CONFIRMADO);
        ESTADOS_POSIBLES.add(RECHAZADO);
        ESTADOS_POSIBLES.add(DERIVADO);
        ESTADOS_POSIBLES.add(BLOQUEADO_EN_REVISION);
    }

    private final String descripcion;

    private Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean esRechazado() {
        return "Rechazado".equals(descripcion);
    }

    public boolean esAmbitoEventoSismico() {
        return ESTADOS_POSIBLES.contains(this);
    }

    public static Estado BuscarEstadoRechazado() {
        return ESTADOS_POSIBLES.stream()
                .filter(Estado::esRechazado)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Estado 'Rechazado' no encontrado."));
    }

    public boolean esPendienteRevision() {
        return "Pendiente".equals(descripcion);
    }

    public boolean esAutodetectado() {
        return "Pendiente".equals(descripcion); // Ajusta según tu lógica
    }
}