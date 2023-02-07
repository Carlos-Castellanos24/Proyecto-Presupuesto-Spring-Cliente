package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.Login;
import com.sv.ProyectoPresupuesto.clases.Usuario;
import com.sv.ProyectoPresupuesto.exceptions.LoginNotFoundException;
import java.util.List;

public interface LoginService {
    
    public List<Login> listarLogin();
    
    public void insertarLogin(String clave, String correo, String estadoLogin, Usuario usuario, String estadoUsuario);
    
    public void eliminarLogin(Login login);
    
    public Login buscarLogin(Login login);
    
    public Login buscarLoginPorCorreo(String correo);
    
    public Login buscarLoginPorDui(String dui) throws LoginNotFoundException;
    
    public Login validarLogin(String correo);
    
    public void actualizarCorreoLogin(String correo, Integer idLogin);
    
    public Login buscarLoginPorId(Integer idLogin);
    
    public void actualizarEstadoLogin(Integer idLogin);
    
    /*METODOS PARA TOKEN EMAIL*/
    
    public void restablecerPassword(String token, String correo) throws LoginNotFoundException;
    
    public Login get(String passwordToken);
    
    public void actualizarPassword(Login login, String nuevaPassword);
}
