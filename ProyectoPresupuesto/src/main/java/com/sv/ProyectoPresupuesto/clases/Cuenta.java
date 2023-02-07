package com.sv.ProyectoPresupuesto.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @ManyToOne
    @JoinColumn(name = "id_login", referencedColumnName = "id_login")
    private Login login;

    @Column(name = "numero_cuenta", unique = true)
    @NotEmpty
    private String numeroCuenta;

    @Column(name = "saldo_disponible")
    @NotEmpty
    private double saldoDisponible;
}
