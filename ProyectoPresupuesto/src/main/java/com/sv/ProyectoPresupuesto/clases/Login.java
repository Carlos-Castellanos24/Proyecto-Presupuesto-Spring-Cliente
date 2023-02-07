package com.sv.ProyectoPresupuesto.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "login")
public class Login implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login")
    private Integer idLogin;
    
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
    
    @Column(name = "clave")
    @NotEmpty
    private String clave;
    
    @Column(name = "correo")
    @NotEmpty
    private String correo;
    
    @Column(name = "estado_login")
    @NotEmpty
    private String estadoLogin;
    
    @Column(name = "password_token")
    private String passwordToken;
}
