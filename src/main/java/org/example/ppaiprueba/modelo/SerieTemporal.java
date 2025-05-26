package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerieTemporal {
    private String condicionAlarma;
    private LocalDateTime fechaHoraInicioRegistroMuestras;
    private LocalDateTime fechaHoraRegistro;
    private double frecuenciaMuestreo;
    private List<MuestraSismica> muestraSismicas;
    private Sismografo sismografo;

    public SerieTemporal(String condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestras, LocalDateTime fechaHoraRegistro, double frecuenciaMuestreo, List<MuestraSismica> muestraSismicas, Sismografo sismografo) {
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.condicionAlarma = condicionAlarma;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.muestraSismicas = new ArrayList<>(muestraSismicas);
        this.sismografo = sismografo;
    }
    public Object[][] getDatos(){
        Object[] vectorDatos = new Object[6];
        vectorDatos[0] = condicionAlarma;
        vectorDatos[1] = fechaHoraInicioRegistroMuestras.toString();
        vectorDatos[2] =  fechaHoraRegistro.toString();
        vectorDatos[3] = String.valueOf(frecuenciaMuestreo);
        vectorDatos[4] = obtenerMuestras();

        Object[][] vectorID = new String[1][2];
        vectorID[0][0] = sismografo.getIdEstacionSismologica();
        vectorID[0][1] = vectorDatos;

        return vectorID;
    }
    public Object[] obtenerMuestras(){
        Object[] datosMuestras = new String[muestraSismicas.size()];
        for (int i = 0; i < muestraSismicas.size(); i++){
            MuestraSismica muestra = muestraSismicas.get(i);
            datosMuestras[i] = muestra.getDatos();
        }
        return datosMuestras;
    }
}
