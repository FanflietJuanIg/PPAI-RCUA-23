package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MuestraSismica {
    private LocalDateTime fechaHoraMuestra;
    private List<DetalleMuestraSismica> detalleMuestraSismica;
    public MuestraSismica(LocalDateTime fechaHoraMuestra, List<DetalleMuestraSismica> detalleMuestraSismica){
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detalleMuestraSismica = new ArrayList<>(detalleMuestraSismica);
    }


    public String[] getDatos(){
        String[] vectorDatos = new String[2];
        vectorDatos[0] = fechaHoraMuestra.toString();
        vectorDatos[1] = Arrays.toString(obtenerDetalles());
        return vectorDatos;
    }
    public String[] obtenerDetalles(){
        String[] datosDetalles = new String[detalleMuestraSismica.size()];
        for (int i = 0; i < detalleMuestraSismica.size();i++){
            datosDetalles[i] = Arrays.toString(detalleMuestraSismica.get(i).getDatos());
        }
        return datosDetalles;
    }
}
