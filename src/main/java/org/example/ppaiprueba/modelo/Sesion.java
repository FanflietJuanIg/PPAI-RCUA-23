package org.example.ppaiprueba.modelo;

public class Sesion {
    private Usuario usuarioAutenticado;

    public Sesion(Usuario usuario) {
        this.usuarioAutenticado = usuario;
    }

    public Usuario getUsuario() {
        return usuarioAutenticado;
    }

    public Empleado getEmpleadoLogueado() {
        return usuarioAutenticado.getEmpleado();
    }
}
