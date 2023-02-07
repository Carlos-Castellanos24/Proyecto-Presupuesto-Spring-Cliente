package com.sv.ProyectoPresupuesto.controladores;

import com.sv.ProyectoPresupuesto.clases.*;
import com.sv.ProyectoPresupuesto.exceptions.IngresoException;
import com.sv.ProyectoPresupuesto.servicio.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"idLogin", "idCuenta"})
public class ControladorIngreso {

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private EgresoService egresoService;

    @GetMapping("/ingreso")
    public String ingreso() {
        return "ingreso/ingreso";
    }

    @PostMapping("/insertarIngreso")
    public String insertarIngreso(@Valid Ingreso ingreso, Errors errores, Model model, @RequestParam("saldoIngreso") double saldoIngreso,
            @RequestParam("descripcion") String descripcion, @RequestParam("fechaIngreso") String fechaIngreso,
            @RequestParam("estadoIngreso") String estadoIngreso) throws ParseException {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }
        if (errores.hasErrors()) {
            return "redirect:/ingreso";
        }
        if (ingreso != null && (Integer) model.getAttribute("idLogin") != 0) {
            Cuenta cuentaUsuario = cuentaService.buscarCuentaPorId((Integer) model.getAttribute("idCuenta"));
            if (cuentaUsuario != null) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    ingresoService.insertarIngreso(saldoIngreso, descripcion, formato.parse(fechaIngreso), cuentaUsuario, estadoIngreso);
                    cuentaService.actualizarSaldoCuenta(cuentaUsuario.getIdCuenta(), cuentaUsuario.getSaldoDisponible() + saldoIngreso);
                } catch (IngresoException ie) {
                    model.addAttribute("errorIngreso", ie.getMessage());
                    return "ingreso/ingreso";
                }
            }
        }
        return "redirect:/index";
    }

    @GetMapping("/detallesIngreso/{idIngreso}")
    public String detallesIngreso(@PathVariable("idIngreso") Integer idIngreso, Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if (idIngreso != 0) {
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

            var detalleIngreso = ingresoService.buscarIngresoPorIdIngreso(idIngreso);
            model.addAttribute("detalleIngreso", detalleIngreso);
        }
        return "ingreso/detallesIngreso";
    }

    @GetMapping("/eliminarIngreso/{idIngreso}")
    public String eliminarIngreso(@PathVariable("idIngreso") Integer idIngreso, Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if (idIngreso != 0) {
            ingresoService.actualizarEstadoIngreso(idIngreso);
        }
        return "redirect:/index";
    }

    @GetMapping("/ingresos")
    public String ingresos(Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if ((Integer) model.getAttribute("idCuenta") != 0) {
            var ingresos = ingresoService.listarTodosLosIngresos((Integer) model.getAttribute("idCuenta"));
            model.addAttribute("ingresos", ingresos);

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
        return "ingreso/listaIngresos";
    }
}
