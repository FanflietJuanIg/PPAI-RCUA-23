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


    public Object[] getDatos() {
        Object[] vectorDatos = new Object[2];
        vectorDatos[0] = fechaHoraMuestra.toString();
        vectorDatos[1] = obtenerDetalles();
        return vectorDatos;
    }
    public String[][] obtenerDetalles(){
        String[][] datosDetalles = new String[detalleMuestraSismica.size()][2];
        for (int i = 0; i < detalleMuestraSismica.size();i++){
            DetalleMuestraSismica detalle = detalleMuestraSismica.get(i);
            String[] datosTemp = detalle.getDatos();
            datosDetalles[i][0] = datosTemp[0];
            datosDetalles[i][1] = datosTemp[1];
        }
        return datosDetalles;
    }
}