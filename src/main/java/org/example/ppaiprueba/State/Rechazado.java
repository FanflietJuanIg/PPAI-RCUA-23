package org.example.ppaiprueba.State;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "EstadoRechazado")
public class Rechazado extends Estado {
    public Rechazado() {
        super("Rechazado");
    }
}
