package com.sv.ProyectoPresupuesto.servicio;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import com.sv.ProyectoPresupuesto.clases.Ingreso;
import com.sv.ProyectoPresupuesto.exceptions.IngresoException;
import java.util.Date;
import java.util.List;

public interface IngresoService {
    
    public List<Ingreso> listarIngresos();
    
    public void insertarIngreso(double saldoIngreso, String descripcion, Date fechaDate, Cuenta cuenta, String estadoIngreso) 
            throws IngresoException;
    
    public void eliminarIngreso(Ingreso ingreso);
    
    public Ingreso buscarIngreso(Ingreso ingreso);
    
    public List<Ingreso> buscarIngresosPorIdCuenta(Integer idCuenta);
    
    public Ingreso buscarIngresoPorIdIngreso(Integer idIngreso); 
    
    public Ingreso obtenerRegistroIngresado(Cuenta cuenta);
    
    public void actualizarEstadoIngreso(Integer idIngreso);
    
    public List<Ingreso> listarTodosLosIngresos(Integer idCuenta);
}
