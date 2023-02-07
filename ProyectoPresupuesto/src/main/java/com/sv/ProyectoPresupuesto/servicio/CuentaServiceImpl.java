package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import com.sv.ProyectoPresupuesto.clases.Login;
import com.sv.ProyectoPresupuesto.dao.CuentaDAO;
import com.sv.ProyectoPresupuesto.exceptions.CuentaNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaServiceImpl implements CuentaService{

    @Autowired
    private CuentaDAO cuentaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> listarCuentas() {
        return (List<Cuenta>) cuentaDao.findAll();
    }

    @Override
    @Transactional
    public void insertarCuenta(Login loginEncontrado, String numeroCuenta, double saldoDisponible){
        if (numeroCuenta != null && saldoDisponible == 0.0) {                       
            Cuenta cuenta = new Cuenta();
            cuenta.setLogin(loginEncontrado);
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setSaldoDisponible(saldoDisponible);           
            cuentaDao.save(cuenta);
        }
    }

    @Override
    @Transactional
    public void eliminarCuenta(Cuenta cuenta){
        if (cuenta != null) {
            cuentaDao.delete(cuenta);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta buscarCuenta(Cuenta cuenta){
        if (cuenta != null && cuenta.getIdCuenta() != 0) {
            return cuentaDao.findById(cuenta.getIdCuenta()).orElse(null);
        }else{
            return null;
        }
    }   

    @Override
    @Transactional(readOnly = true)
    public Cuenta buscarCuentaPorIdLogin(Integer idLogin){
        if (idLogin != 0) {
            return cuentaDao.buscarCuentaPorIdLogin(idLogin);
        }else{
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta buscarCuentaPorId(Integer idCuenta){
        if (idCuenta != 0) {
            return cuentaDao.buscarCuentaPorId(idCuenta);
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void actualizarSaldoCuenta(Integer idCuenta, double saldoDisponible) {
        if (idCuenta != 0 && saldoDisponible != 0.0) {
            cuentaDao.actualizarSaldoCuenta(idCuenta, saldoDisponible);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta buscarCuentaPorNumeroCuenta(String numeroCuenta) throws CuentaNotFoundException{
        Cuenta cuenta = cuentaDao.buscarCuentaPorNumeroCuenta(numeroCuenta);       
        if (cuenta != null) {
            return cuenta;           
        }else{
           throw new CuentaNotFoundException("No se encontro el numero de cuenta: " + numeroCuenta);
        }
    }

    @Override
    @Transactional
    public void actualizarNumeroCuenta(Integer numeroCuenta, Integer idCuenta) {
        if (numeroCuenta != null && idCuenta != 0) {
            cuentaDao.actualizarNumeroCuenta(numeroCuenta, idCuenta);
        }
    }
}
