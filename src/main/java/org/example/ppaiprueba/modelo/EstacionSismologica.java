package org.example.ppaiprueba.modelo;

public class EstacionSismologica {
    private String codigoEstacion;
    private String documentoCertificacionAdq;
    private String fechaSolicitudCertificacion;
    private double latitud;
    private double longitud;
    private String nombre;
    private String nroCertificacionAdquisicion;

    public EstacionSismologica(String codigoEstacion, String documentoCertificacionAdq,
                               String fechaSolicitudCertificacion, double latitud, double longitud,
                               String nombre, String nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificacionAdq = documentoCertificacionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    public String getCodigoEstacion() {
        return codigoEstacion;
    }

    public String getNombre() {
        return nombre;
    }

    // Otros getters y setters pueden agregarse según se necesiten
}
