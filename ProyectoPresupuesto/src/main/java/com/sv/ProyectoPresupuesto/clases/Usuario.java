package com.sv.ProyectoPresupuesto.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
    
    @Column(name = "apellido")
    @NotEmpty
    private String apellido;
    
    @Column(name = "dui", unique = true)
    @NotEmpty
    private String dui;
    
    @Column(name = "telefono")
    @NotEmpty
    private String telefono;
    
    @Column(name = "estado_usuario")
    @NotEmpty
    private String estadoUsuario;
}
