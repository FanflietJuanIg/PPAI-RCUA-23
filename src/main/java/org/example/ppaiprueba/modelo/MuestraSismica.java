package org.example.ppaiprueba.modelo;

import java.time.LocalDateTime;

public class MuestraSismica {
    private LocalDateTime fechaHoraMuestra;
    private DetalleMuestraSismica[] detalleMuestraSismica;
    public MuestraSismica(LocalDateTime fechaHoraMuestra, DetalleMuestraSismica[] detalleMuestraSismica){
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detalleMuestraSismica = detalleMuestraSismica;
    }


    public Object[] getDatos(){
        Object[] vectorDatos = new Object[2];
        vectorDatos[0] = fechaHoraMuestra;
        vectorDatos[1] = obtenerDetalles();
        return vectorDatos;
    }

    public object[][] obetenerDetalles(){
        Object[] vectorDatos = new Object[2];
        Object[][] vectorContenedor = new Object[1][];
        for (int i = 0; i < vectorDatos.length; i++){
            vectorDatos[0] = detalleMuestraSismica[i].getValor();
            vectorContenedor[1][i] = vectorDatos;
        }

        return vectorContenedor;
    }
}
