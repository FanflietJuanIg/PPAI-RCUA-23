package org.example.ppaiprueba.State;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Confirmado")
public class Confirmado extends Estado {

    public Confirmado() {
        super("Confirmado");
    }
}
