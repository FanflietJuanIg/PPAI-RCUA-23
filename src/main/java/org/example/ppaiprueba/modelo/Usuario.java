package org.example.ppaiprueba.modelo;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;

    // Asociación con un empleado (opcional, según tu modelo completo)
    private Empleado empleado;

    public Usuario(String nombreUsuario, String contrasena, Empleado empleado) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.empleado = empleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public boolean autenticar(String usuarioIngresado, String contrasenaIngresada) {
        return this.nombreUsuario.equals(usuarioIngresado) && this.contrasena.equals(contrasenaIngresada);
    }

}
