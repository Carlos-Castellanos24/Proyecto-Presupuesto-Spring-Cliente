package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.*;
import com.sv.ProyectoPresupuesto.exceptions.CuentaNotFoundException;
import java.util.List;

public interface CuentaService {
    
    public List<Cuenta> listarCuentas();
    
    public void insertarCuenta(Login loginEncontrado,String numeroCuenta, double saldoDisponible);
    
    public void eliminarCuenta(Cuenta cuenta);
    
    public void actualizarSaldoCuenta(Integer idCuenta, double saldoDisponible);
    
    public Cuenta buscarCuenta(Cuenta cuenta);
    
    public Cuenta buscarCuentaPorIdLogin(Integer idLogin);   
    
    public Cuenta buscarCuentaPorId(Integer idCuenta);
    
    public Cuenta buscarCuentaPorNumeroCuenta(String numeroCuenta) throws CuentaNotFoundException;    
    
    public void actualizarNumeroCuenta(Integer numeroCuenta, Integer idCuenta);
}
