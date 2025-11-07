package org.example.ppaiprueba.State;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "EstadoDerivado")
public class Derivado extends Estado {

    public Derivado() {
        super("Derivado");
    }
}
