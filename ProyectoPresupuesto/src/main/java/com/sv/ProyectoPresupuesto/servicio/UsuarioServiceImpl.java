package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.Usuario;
import com.sv.ProyectoPresupuesto.dao.UsuarioDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    @Transactional
    public void insertarUsuario(Usuario usuario, String estadoUsuario) {
        if (usuario != null && estadoUsuario != null) {
            usuario.setEstadoUsuario(estadoUsuario);
            usuarioDao.save(usuario);
        }
    }

    @Override
    @Transactional
    public void eliminarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarioDao.delete(usuario);
        }
    }

    @Override
    @Transactional
    public Usuario buscarUsuario(Usuario usuario) {
        if (usuario != null && usuario.getIdUsuario() != 0) {
            return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        if (idUsuario != 0) {
            return usuarioDao.buscarUsuarioPorId(idUsuario);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void actualizarDatosUsuario(String nombre, String apellido, String dui, String telefono, Integer idUsuario) {

        if (idUsuario != 0 && nombre != null && apellido != null && dui != null
                && telefono != null) {
            usuarioDao.actualizarDatosUsuario(nombre, apellido, dui, telefono, idUsuario);
        }
    }

    @Override
    @Transactional
    public void actualizarEstadoUsuario(Integer idUsuario) {
        if (idUsuario != 0) {
            usuarioDao.actualizarEstadoUsuario(idUsuario);
        }
    }
}
