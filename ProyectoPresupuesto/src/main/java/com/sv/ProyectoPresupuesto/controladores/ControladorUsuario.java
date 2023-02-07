package com.sv.ProyectoPresupuesto.controladores;

import com.sv.ProyectoPresupuesto.clases.*;
import com.sv.ProyectoPresupuesto.exceptions.LoginNotFoundException;
import com.sv.ProyectoPresupuesto.servicio.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"idLogin", "idCuenta"})
public class ControladorUsuario {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/insertarUsuario")
    public String insertarUsuario(@Valid Usuario usuario, Errors errores, Model model,
            @RequestParam("estadoUsuario") String estadoUsuario, @RequestParam("clave") String clave,
            @RequestParam("correo") String correo, @RequestParam("estadoLogin") String estadoLogin,
            @RequestParam("dui") String dui) throws LoginNotFoundException {

        if (errores.hasErrors()) {
            return "redirect:/registro";
        }
        if (usuario != null && estadoUsuario != null) {
            usuarioService.insertarUsuario(usuario, estadoUsuario);
            loginService.insertarLogin(clave, correo, estadoLogin, usuario, estadoUsuario);

            if (correo != null) {

                Login loginEncontrado = loginService.buscarLoginPorDui(dui);
                if (loginEncontrado != null) {
                    cuentaService.insertarCuenta(loginEncontrado, Numero.generarNumeroCuenta(), 0.0);
                }
            }
        }
        return "redirect:/";
    }
}
