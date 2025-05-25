package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;

public class SerieTemporal {
    private String condicionAlarma;
    private LocalDateTime fechaHoraInicioRegistroMuestras;
    private LocalDateTime fechaHoraRegistro;
    private double frecuenciaMuestreo;

    public SerieTemporal(String condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestras, LocalDateTime fechaHoraRegistro, double frecuenciaMuestreo) {
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.condicionAlarma = condicionAlarma;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }
    public Object[] getDatos(){
        Object[] vectorDatos = new Object[4];
        vectorDatos[0] = condicionAlarma;
        vectorDatos[1] = fechaHoraInicioRegistroMuestras;
        vectorDatos[2] =  fechaHoraRegistro;
        vectorDatos[3] = frecuenciaMuestreo;
        return vectorDatos;
    }
}
