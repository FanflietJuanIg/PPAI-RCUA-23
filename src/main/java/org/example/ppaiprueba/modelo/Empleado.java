package org.example.ppaiprueba.modelo;

public class Empleado {
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;

    private String rol; // relación con la clase Rol

    public Empleado(String nombre, String apellido, String mail, String telefono, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String obtenerMail() {
        return this.mail;
    }
}
