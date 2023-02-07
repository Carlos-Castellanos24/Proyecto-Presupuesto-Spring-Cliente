package com.sv.ProyectoPresupuesto.clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "ingreso")
public class Ingreso implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso")
    private Integer idIngreso;
    
    @ManyToOne
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta")
    private Cuenta cuenta;
    
    @Column(name = "saldo_ingreso")
    @NotEmpty
    private double saldoIngreso;
    
    @Column(name = "descripcion")
    @NotEmpty
    private String descripcion;
    
    @NotEmpty
    @Column(name = "fecha_ingreso")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;
    
    @Column(name = "estado_ingreso")
    @NotEmpty
    private String estadoIngreso; 
}
