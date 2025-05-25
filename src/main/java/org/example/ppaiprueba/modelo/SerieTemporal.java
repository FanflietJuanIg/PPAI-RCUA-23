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

    public SerieTemporal(String condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestras, LocalDateTime fechaHoraRegistro, double frecuenciaMuestreo, List<MuestraSismica> muestraSismicas) {
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.condicionAlarma = condicionAlarma;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.muestraSismicas = new ArrayList<>(muestraSismicas);
    }
    public String[] getDatos(){
        String[] vectorDatos = new String[5];
        vectorDatos[0] = condicionAlarma;
        vectorDatos[1] = fechaHoraInicioRegistroMuestras.toString();
        vectorDatos[2] =  fechaHoraRegistro.toString();
        vectorDatos[3] = String.valueOf(frecuenciaMuestreo);
        vectorDatos[4] = Arrays.toString(obtenerMuestras());
        return vectorDatos;
    }
    public String[] obtenerMuestras(){
        String[] datosMuestras = new String[muestraSismicas.size()];
        for (int i = 0; i < muestraSismicas.size(); i++){
            datosMuestras[i] = Arrays.toString(muestraSismicas.get(i).getDatos());
        }
        return datosMuestras;
    }
}
