package com.sv.ProyectoPresupuesto.controladores;

import com.sv.ProyectoPresupuesto.clases.Cuenta;
import com.sv.ProyectoPresupuesto.clases.Login;
import com.sv.ProyectoPresupuesto.clases.Usuario;
import com.sv.ProyectoPresupuesto.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"idLogin", "idCuenta"})
public class ControladorCuenta {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public String perfil(Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if (model.getAttribute("idLogin") != null && model.getAttribute("idCuenta") != null) {

            var cuentaEncontrada = cuentaService.buscarCuentaPorIdLogin((Integer) model.getAttribute("idLogin"));
            model.addAttribute("cuentaEncontrada", cuentaEncontrada);
            model.addAttribute("idCuenta", cuentaEncontrada.getIdCuenta());

            var ingresosUsuario = ingresoService.buscarIngresosPorIdCuenta(cuentaEncontrada.getIdCuenta());
            model.addAttribute("ingresosUsuario", ingresosUsuario);

            var egresosUsuario = egresoService.buscarEgresosPorIdCuenta(cuentaEncontrada.getIdCuenta());
            model.addAttribute("egresosUsuario", egresosUsuario);

            var ultimoIngreso = ingresoService.obtenerRegistroIngresado(cuentaEncontrada);
            model.addAttribute("ultimoIngreso", ultimoIngreso);

            var ultimoEgreso = egresoService.obtenerRegistroIngresadoEgreso(cuentaEncontrada);
            model.addAttribute("ultimoEgreso", ultimoEgreso);
        }
        return "perfil/perfil";
    }

    @GetMapping("/actualizarPerfil/{idCuenta}")
    public String actualizarPerfil(@PathVariable("idCuenta") Integer idCuenta, Cuenta cuenta, Login login,
            Usuario usuario, Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        cuenta = cuentaService.buscarCuenta(cuenta);
        model.addAttribute("cuenta", cuenta);

        login = loginService.buscarLoginPorId((Integer) model.getAttribute("idLogin"));
        model.addAttribute("login", login);

        usuario = usuarioService.buscarUsuario(login.getUsuario());
        model.addAttribute("usuario", usuario);

        return "perfil/actualizarPerfil";
    }

    @PostMapping("/actualizarDatosPerfil")
    public String actualizarDatosPerfil(@RequestParam("idUsuario") Integer idUsuario,
            @RequestParam("idLogin") Integer idLogin,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("dui") String dui,
            @RequestParam("telefono") String telefono,
            @RequestParam("correo") String correo) {

        if (idLogin != 0 && correo != null) {
            loginService.actualizarCorreoLogin(correo, idLogin);
        }
        if (idUsuario != 0 && nombre != null && apellido != null && dui != null
                && telefono != null) {
            usuarioService.actualizarDatosUsuario(nombre, apellido, dui, telefono, idUsuario);
        }
        return "redirect:/perfil";
    }

    @GetMapping("/eliminarCuenta/{idCuenta}")
    public String eliminarCuenta(@PathVariable("idCuenta") Integer idCuenta, Model model) {
        if (idCuenta != 0) {

            var cuentaEliminar = cuentaService.buscarCuentaPorId(idCuenta);
            if (cuentaEliminar != null) {
                usuarioService.actualizarEstadoUsuario(cuentaEliminar.getLogin().getUsuario().getIdUsuario());
                loginService.actualizarEstadoLogin(cuentaEliminar.getLogin().getIdLogin());
            }
        } else {
            return "redirect:/eliminarCuenta/" + idCuenta;
        }
        return "redirect:/cerrarSesion";
    }
}
