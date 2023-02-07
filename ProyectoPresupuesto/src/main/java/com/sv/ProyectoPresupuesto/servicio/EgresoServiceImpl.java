package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import com.sv.ProyectoPresupuesto.clases.Egreso;
import com.sv.ProyectoPresupuesto.dao.EgresoDAO;
import com.sv.ProyectoPresupuesto.exceptions.EgresoException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EgresoServiceImpl implements EgresoService {

    @Autowired
    private EgresoDAO egresoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Egreso> listarEgresos() {
        return (List<Egreso>) egresoDao.findAll();
    }

    @Override
    @Transactional
    public void insertarEgreso(double saldoEgreso, String descripcion, Date fechaEgreso, String estadoEgreso, Cuenta cuenta) throws EgresoException {
        if (saldoEgreso != 0 && descripcion != null && fechaEgreso != null && cuenta != null) {
            if (saldoEgreso < 9999.99) {
                Egreso egreso = new Egreso();
                egreso.setCuenta(cuenta);
                egreso.setSaldoEgreso(saldoEgreso);
                egreso.setDescripcion(descripcion);
                egreso.setFechaEgreso(fechaEgreso);
                egreso.setEstadoEgreso(estadoEgreso);
                egresoDao.save(egreso);
            }else{
                throw new EgresoException("El egreso excede la cantidad de dinero establecida.");
            }
        }
    }

    @Override
    @Transactional
    public void eliminarEgreso(Egreso egreso) {
        if (egreso != null) {
            egresoDao.delete(egreso);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Egreso buscarEgreso(Egreso egreso) {
        if (egreso != null && egreso.getIdEgreso() != 0) {
            return egresoDao.findById(egreso.getIdEgreso()).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Egreso> buscarEgresosPorIdCuenta(Integer idCuenta) {
        if (idCuenta != 0) {
            return egresoDao.buscarEgresosPorIdCuenta(idCuenta);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Egreso buscarEgresoPorIdEgreso(Integer idEgreso) {
        if (idEgreso != 0) {
            return egresoDao.buscarEgresoPorIdEgreso(idEgreso);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Egreso obtenerRegistroIngresadoEgreso(Cuenta cuenta) {
        Egreso ultimoEgreso = egresoDao.findFirstByCuentaOrderByIdEgresoDesc(cuenta);

        if (ultimoEgreso != null) {
            return ultimoEgreso;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void actualizarEstadoEgreso(Integer idEgreso) {
        if (idEgreso != 0) {
            egresoDao.actualizarEstadoEgreso(idEgreso);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Egreso> listarTodosLosEgresos(Integer idEgreso) {
        if (idEgreso != 0) {
            return egresoDao.listarTodosLosEgresos(idEgreso);
        } else {
            return null;
        }
    }
}
