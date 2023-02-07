package com.sv.ProyectoPresupuesto.dao;

import com.sv.ProyectoPresupuesto.clases.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LoginDAO extends JpaRepository<Login, Integer>{
    
    @Query("SELECT l FROM Login l WHERE l.correo =:correo")
    public Login buscarLoginPorCorreo(String correo);
    
    @Query("SELECT l FROM Login l WHERE l.correo = :correo AND l.estadoLogin = 'A'")
    public Login validarLogin(String correo);
    
    @Modifying
    @Query("UPDATE Login l SET l.correo =:correo WHERE l.idLogin =:idLogin")
    public void actualizarCorreoLogin(String correo, Integer idLogin);
    
    @Query("SELECT l FROM Login l WHERE l.idLogin =:idLogin")
    public Login buscarLoginPorId(Integer idLogin);
    
    @Modifying
    @Query("UPDATE Login l SET l.estadoLogin = 'I' WHERE l.idLogin =:idLogin")
    public void actualizarEstadoLogin(Integer idLogin);
    
    @Query("SELECT l FROM Login l WHERE l.usuario.dui =:dui")
    public Login buscarLoginPorDui(String dui);
    
    public Login findByPasswordToken(String token);
}

