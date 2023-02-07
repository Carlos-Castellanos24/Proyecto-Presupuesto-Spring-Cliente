package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import com.sv.ProyectoPresupuesto.clases.Egreso;
import com.sv.ProyectoPresupuesto.exceptions.EgresoException;
import java.util.Date;
import java.util.List;

public interface EgresoService {
    
    public List<Egreso> listarEgresos();  
    
    public List<Egreso> listarTodosLosEgresos(Integer idEgreso);
    
    public void insertarEgreso(double saldoIngreso, String descripcion, Date fechaDate, String estadoEgreso, Cuenta cuenta)
            throws EgresoException;
    
    public void eliminarEgreso(Egreso egreso);
    
    public Egreso buscarEgreso(Egreso egreso);
    
    public List<Egreso> buscarEgresosPorIdCuenta(Integer idCuenta);
    
    public Egreso buscarEgresoPorIdEgreso(Integer idEgreso);
    
    public Egreso obtenerRegistroIngresadoEgreso(Cuenta cuenta);
    
    public void actualizarEstadoEgreso(Integer idEgreso);
}
