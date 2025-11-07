package org.example.ppaiprueba.State;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "EstadoPendienteDeRevision")
public class PendienteDeRevision extends Estado {

    public PendienteDeRevision() {super("Pendiente de Revision");}
}
