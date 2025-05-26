package org.example.ppaiprueba.modelo;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;

    // Asociación con un empleado
    private Empleado empleado;

    // Atributo estático para simular el usuario logueado
    private static Usuario usuarioLogueado;

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
    // metodo para conseguir al usuario (Creamos uno nosotros por ahora)
    public static Usuario getUsuarioLogueado() {
        if (usuarioLogueado == null) {
            // Simulación: crea un usuario predeterminado con rol "Analista en Sismos"
            Empleado empleadoPredeterminado = new Empleado("Juan", "Pérez", "jperez@mail.com", "123456789", "Analista en Sismos");
            usuarioLogueado = new Usuario("AS123", "password123", empleadoPredeterminado);
        }
        return usuarioLogueado;
    }
}