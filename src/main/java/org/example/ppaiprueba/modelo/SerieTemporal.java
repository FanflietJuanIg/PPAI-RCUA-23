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
    public String[][] getDatos(){
        String[] vectorDatos = new String[6];
        vectorDatos[0] = condicionAlarma;
        vectorDatos[1] = fechaHoraInicioRegistroMuestras.toString();
        vectorDatos[2] =  fechaHoraRegistro.toString();
        vectorDatos[3] = String.valueOf(frecuenciaMuestreo);
        vectorDatos[4] = Arrays.toString(obtenerMuestras());

        String[][] vectorID = new String[1][2];
        vectorID[0][0] = sismografo.getIdEstacionSismologica();
        vectorID[0][1] = Arrays.toString(vectorDatos);

        return vectorID;
    }
    public String[] obtenerMuestras(){
        String[] datosMuestras = new String[muestraSismicas.size()];
        for (int i = 0; i < muestraSismicas.size(); i++){
            datosMuestras[i] = Arrays.toString(muestraSismicas.get(i).getDatos());
        }
        return datosMuestras;
    }
}
