package com.sv.ProyectoPresupuesto.dao;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import com.sv.ProyectoPresupuesto.clases.Ingreso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IngresoDAO extends JpaRepository<Ingreso, Integer>{
    
    @Query("SELECT i FROM Ingreso i WHERE i.cuenta.idCuenta =:idCuenta AND i.estadoIngreso = 'A'")
    public List<Ingreso> buscarIngresosPorIdCuenta(Integer idCuenta);

    @Query("SELECT i FROM Ingreso i WHERE i.idIngreso =:idIngreso")
    public Ingreso buscarIngresoPorIdIngreso(Integer idIngreso);  
    
    public Ingreso findFirstByCuentaOrderByIdIngresoDesc(Cuenta cuenta);
    
    @Query("UPDATE Ingreso i SET i.estadoIngreso = 'I' WHERE i.idIngreso =:idIngreso")
    @Modifying
    public void actualizarEstadoIngreso(Integer idIngreso); 
    
    @Query("SELECT i FROM Ingreso i WHERE i.cuenta.idCuenta =:idCuenta")
    public List<Ingreso> listarTodosLosIngresos(Integer idCuenta);   
}
