package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> listarUsuarios();

    public void insertarUsuario(Usuario usuario, String estadoUsuario);

    public void eliminarUsuario(Usuario usuario);

    public Usuario buscarUsuario(Usuario usuario);

    public Usuario buscarUsuarioPorId(Integer idUsuario);

    public void actualizarDatosUsuario(String nombre, String apellido, String dui, String telefono, Integer idUsuario);

    public void actualizarEstadoUsuario(Integer idUsuario);
}
