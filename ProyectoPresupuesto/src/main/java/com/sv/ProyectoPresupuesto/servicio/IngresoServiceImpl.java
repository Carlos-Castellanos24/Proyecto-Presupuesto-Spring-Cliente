package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import com.sv.ProyectoPresupuesto.clases.Ingreso;
import com.sv.ProyectoPresupuesto.dao.IngresoDAO;
import com.sv.ProyectoPresupuesto.exceptions.IngresoException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngresoServiceImpl implements IngresoService {

    @Autowired
    private IngresoDAO ingresoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ingreso> listarIngresos() {
        return (List<Ingreso>) ingresoDao.findAll();
    }

    @Override
    @Transactional
    public void insertarIngreso(double saldoIngreso, String descripcion, Date fechaDate, Cuenta cuenta, String estadoIngreso) throws IngresoException {
        if (saldoIngreso != 0 && descripcion != null && fechaDate != null && cuenta != null && estadoIngreso != null) {
            if (saldoIngreso < 9999.99) {
                Ingreso ingreso = new Ingreso();
                ingreso.setCuenta(cuenta);
                ingreso.setSaldoIngreso(saldoIngreso);
                ingreso.setDescripcion(descripcion);
                ingreso.setFechaIngreso(fechaDate);
                ingreso.setEstadoIngreso(estadoIngreso);
                ingresoDao.save(ingreso);
            }else{
                throw new IngresoException("El ingreso excede la cantidad de dinero establecida.");
            }
        }
    }

    @Override
    @Transactional
    public void eliminarIngreso(Ingreso ingreso) {
        if (ingreso != null) {
            ingresoDao.delete(ingreso);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Ingreso buscarIngreso(Ingreso ingreso) {
        if (ingreso != null && ingreso.getIdIngreso() != 0) {
            return ingresoDao.findById(ingreso.getIdIngreso()).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ingreso> buscarIngresosPorIdCuenta(Integer idCuenta) {
        if (idCuenta != 0) {
            return ingresoDao.buscarIngresosPorIdCuenta(idCuenta);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Ingreso buscarIngresoPorIdIngreso(Integer idIngreso) {
        if (idIngreso != 0) {
            return ingresoDao.buscarIngresoPorIdIngreso(idIngreso);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Ingreso obtenerRegistroIngresado(Cuenta cuenta) {

        Ingreso ultimoIngreso = ingresoDao.findFirstByCuentaOrderByIdIngresoDesc(cuenta);

        if (ultimoIngreso != null) {
            return ultimoIngreso;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void actualizarEstadoIngreso(Integer idIngreso) {
        if (idIngreso != 0) {
            ingresoDao.actualizarEstadoIngreso(idIngreso);
        }
    }

    @Override
    public List<Ingreso> listarTodosLosIngresos(Integer idCuenta) {
        if (idCuenta != 0) {
            return (List<Ingreso>) ingresoDao.listarTodosLosIngresos(idCuenta);
        } else {
            return null;
        }
    }
}
