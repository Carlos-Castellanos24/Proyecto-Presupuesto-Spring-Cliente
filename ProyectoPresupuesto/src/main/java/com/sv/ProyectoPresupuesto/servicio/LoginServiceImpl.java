package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.exceptions.LoginNotFoundException;
import com.sv.ProyectoPresupuesto.Security.EncriptarPassword;
import com.sv.ProyectoPresupuesto.clases.Login;
import com.sv.ProyectoPresupuesto.clases.Usuario;
import com.sv.ProyectoPresupuesto.dao.LoginDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDao;

    @Override
    @Transactional(readOnly = true)
    public List<Login> listarLogin() {
        return (List<Login>) loginDao.findAll();
    }

    @Override
    @Transactional
    public void insertarLogin(String clave, String correo, String estadoLogin, Usuario usuario, String estadoUsuario) {
        if (clave != null && correo != null && estadoLogin != null) {
            Login login = new Login();
            login.setClave(EncriptarPassword.encriptarPassword(clave));
            login.setCorreo(correo);
            usuario.setEstadoUsuario(estadoUsuario);
            login.setUsuario(usuario);
            login.setEstadoLogin(estadoLogin);
            loginDao.save(login);
        }
    }

    @Override
    @Transactional
    public void eliminarLogin(Login login) {
        if (login != null) {
            loginDao.delete(login);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Login buscarLogin(Login login) {
        if (login != null && login.getIdLogin() != 0) {
            return loginDao.findById(login.getIdLogin()).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Login buscarLoginPorCorreo(String correo) {
        if (correo != null) {
            return loginDao.buscarLoginPorCorreo(correo);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Login validarLogin(String correo) {
        if (correo != null) {
            return loginDao.validarLogin(correo);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void actualizarCorreoLogin(String correo, Integer idLogin) {
        if (correo != null && idLogin != 0) {
            loginDao.actualizarCorreoLogin(correo, idLogin);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Login buscarLoginPorId(Integer idLogin) {
        if (idLogin != 0) {
            return loginDao.buscarLoginPorId(idLogin);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void actualizarEstadoLogin(Integer idLogin) {
        if (idLogin != 0) {
            loginDao.actualizarEstadoLogin(idLogin);
        }
    }

    @Override
    @Transactional
    public void restablecerPassword(String token, String correo)throws LoginNotFoundException {
        Login login = loginDao.buscarLoginPorCorreo(correo);       
        if (login != null) {
            login.setPasswordToken(token);
            loginDao.save(login);
        } else {
            throw new LoginNotFoundException("No se encontro el correo: " + correo);
        }
    }

    @Override
    @Transactional
    public Login get(String passwordToken) {
        return loginDao.findByPasswordToken(passwordToken);
    }

    @Override
    @Transactional
    public void actualizarPassword(Login login, String nuevaPassword) {
        
        String nuevaPasswordEncriptada = EncriptarPassword.encriptarPassword(nuevaPassword);
        login.setClave(nuevaPasswordEncriptada);
        login.setPasswordToken(null);
        loginDao.save(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Login buscarLoginPorDui(String dui) throws LoginNotFoundException{
        if (!dui.equals("")) {
            return loginDao.buscarLoginPorDui(dui);
        }else{
            throw new LoginNotFoundException("No se encontro ningun usuario con el dui: " + dui);
        }
    }
}
