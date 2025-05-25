package org.example.ppaiprueba.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sismografo {
    private EstacionSismologica estacionSismologica;

    public Sismografo(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    public String getIdEstacionSismologica() {
        return estacionSismologica.getCodigoEstacion();
    }

}