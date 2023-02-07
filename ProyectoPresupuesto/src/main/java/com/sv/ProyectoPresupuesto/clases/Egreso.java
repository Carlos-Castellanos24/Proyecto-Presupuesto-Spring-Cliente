package com.sv.ProyectoPresupuesto.clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "egreso")
public class Egreso implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_egreso")
    private Integer idEgreso;
    
    @ManyToOne
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta")
    private Cuenta cuenta;
    
    @Column(name = "saldo_egreso")
    @NotEmpty
    private double saldoEgreso;
    
    @Column(name = "descripcion")
    @NotEmpty
    private String descripcion;
    
    @Column(name = "estado_egreso")
    @NotEmpty
    private String estadoEgreso;    
    
    @NotEmpty
    @Column(name = "fecha_egreso")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaEgreso;
}
