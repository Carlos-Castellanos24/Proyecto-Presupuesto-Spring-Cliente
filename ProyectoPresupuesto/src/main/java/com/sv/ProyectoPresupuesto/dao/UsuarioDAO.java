package com.sv.ProyectoPresupuesto.dao;

import com.sv.ProyectoPresupuesto.clases.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.idUsuario =:idUsuario")
    public Usuario buscarUsuarioPorId(Integer idUsuario);

    @Modifying
    @Query("UPDATE Usuario u SET u.nombre =:nombre, u.apellido =:apellido, u.dui =:dui, u.telefono =:telefono WHERE u.idUsuario =:idUsuario")
    public void actualizarDatosUsuario(String nombre, String apellido, String dui, String telefono, Integer idUsuario);

    @Modifying
    @Query("UPDATE Usuario u SET u.estadoUsuario = 'I' WHERE u.idUsuario =:idUsuario")
    public void actualizarEstadoUsuario(Integer idUsuario);
}
