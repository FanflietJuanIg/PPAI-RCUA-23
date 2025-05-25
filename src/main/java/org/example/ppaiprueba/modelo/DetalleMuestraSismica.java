package org.example.ppaiprueba.modelo;

public class DetalleMuestraSismica {
    private double valor;
    private  TipoDeDato tipoDeDato;

    public DetalleMuestraSismica(double valor, TipoDeDato tipoDeDato){
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }
    public String[] getDatos(){
        String[] vectorDatos = new String[2];
        vectorDatos[0] = String.valueOf(valor);
        vectorDatos[1] = tipoDeDato.getDenominacion();
        return vectorDatos;
    }
}
