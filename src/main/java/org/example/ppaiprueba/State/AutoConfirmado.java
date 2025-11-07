package org.example.ppaiprueba.State;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "EstadoAutoConfirmado")
public class AutoConfirmado extends Estado{

    public AutoConfirmado() {
        super("AutoConfirmado");
    }
}
