package org.example.ppaiprueba.modelo;

public class DetalleMuestraSismica {
    private double valor;
    private  TipoDeDato tipoDeDato;

    public DetalleMuestraSismica(double valor, TipoDeDato tipoDeDato){
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }
    public Object[] getDatos(){
        Object[] vectorDatos = new Object[2];
        vectorDatos[0] = valor;
        vectorDatos[1] = tipoDeDato.getDenominacion();
        return vectorDatos;
    }
}
