package com.sv.ProyectoPresupuesto.dao;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CuentaDAO extends JpaRepository<Cuenta, Integer>{
    
    @Query("SELECT c FROM Cuenta c WHERE c.login.idLogin =:idLogin")
    public Cuenta buscarCuentaPorIdLogin(Integer idLogin);
    
    @Query("SELECT c FROM Cuenta c WHERE c.idCuenta =:idCuenta")
    public Cuenta buscarCuentaPorId(Integer idCuenta);
    
    @Query("UPDATE Cuenta c SET c.saldoDisponible =:saldoDisponible WHERE c.idCuenta =:idCuenta")
    @Modifying
    public void actualizarSaldoCuenta(Integer idCuenta, double saldoDisponible);
    
    @Query("SELECT c FROM Cuenta c WHERE c.numeroCuenta =:numeroCuenta")
    public Cuenta buscarCuentaPorNumeroCuenta(String numeroCuenta);
    
    @Query("UPDATE Cuenta c SET c.numeroCuenta =:numeroCuenta WHERE c.idCuenta =:idCuenta")
    @Modifying
    public void actualizarNumeroCuenta(Integer numeroCuenta, Integer idCuenta);   
}
