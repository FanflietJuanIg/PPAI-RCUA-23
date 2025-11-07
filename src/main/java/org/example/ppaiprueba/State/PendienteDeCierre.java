package org.example.ppaiprueba.State;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "EstadoPendinteDeCierre")
public class PendienteDeCierre extends Estado {

    public PendienteDeCierre() {
        super("Pendiente de Cierre");
    }
}
