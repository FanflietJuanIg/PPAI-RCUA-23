package org.example.ppaiprueba.modelo;

public class OrigenDeGeneracion {
    private String region;
    private String provincia;

    public OrigenDeGeneracion(String region, String provincia) {
        this.region = region;
        this.provincia = provincia;
    }

    public String getRegion() {
        return region;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}