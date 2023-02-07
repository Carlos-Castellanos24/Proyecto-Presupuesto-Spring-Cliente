package com.sv.ProyectoPresupuesto.dao;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import com.sv.ProyectoPresupuesto.clases.Egreso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EgresoDAO extends JpaRepository<Egreso, Integer>{
    
    @Query("SELECT e FROM Egreso e WHERE e.cuenta.idCuenta =:idCuenta AND e.estadoEgreso = 'A'")
    public List<Egreso> buscarEgresosPorIdCuenta(Integer idCuenta);
    
    @Query("SELECT e FROM Egreso e WHERE e.idEgreso =:idEgreso")
    public Egreso buscarEgresoPorIdEgreso(Integer idEgreso);
    
    public Egreso findFirstByCuentaOrderByIdEgresoDesc(Cuenta cuenta);    
    
    @Query("UPDATE Egreso e SET e.estadoEgreso = 'I' WHERE e.idEgreso =:idEgreso")
    @Modifying
    public void actualizarEstadoEgreso(Integer idEgreso);   
    
    @Query("SELECT e FROM Egreso e WHERE e.cuenta.idCuenta =:idCuenta")
    public List<Egreso> listarTodosLosEgresos(Integer idCuenta);    
}
