package org.example.ppaiprueba.State;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "EstadoAnulado")
public class Anulado extends Estado {
    public Anulado() {
        super("Anulado");
    }
}
