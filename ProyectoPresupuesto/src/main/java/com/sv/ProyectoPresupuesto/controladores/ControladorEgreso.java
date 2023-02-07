package com.sv.ProyectoPresupuesto.controladores;

import com.sv.ProyectoPresupuesto.clases.*;
import com.sv.ProyectoPresupuesto.exceptions.CuentaNotFoundException;
import com.sv.ProyectoPresupuesto.exceptions.EgresoException;
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

@Controller
@SessionAttributes({"idLogin", "idCuenta"})
public class ControladorEgreso {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private IngresoService ingresoService;

    @GetMapping("/egreso")
    public String egreso() {
        return "egreso/egreso";
    }

    @PostMapping("/insertarEgreso")
    public String insertarEgreso(@Valid Egreso egreso, Errors errores, Model model, @RequestParam("saldoEgreso") double saldoEgreso,
            @RequestParam("descripcion") String descripcion, @RequestParam("fechaEgreso") String fechaEgreso, @RequestParam("estadoEgreso") String estadoEgreso) throws ParseException {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if (errores.hasErrors()) {
            return "redirect:/ingreso";
        }
        if (egreso != null && (Integer) model.getAttribute("idLogin") != 0) {
            Cuenta cuentaUsuario = cuentaService.buscarCuentaPorId((Integer) model.getAttribute("idCuenta"));
            if (cuentaUsuario != null) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    egresoService.insertarEgreso(saldoEgreso, descripcion, formato.parse(fechaEgreso), estadoEgreso, cuentaUsuario);
                    cuentaService.actualizarSaldoCuenta(cuentaUsuario.getIdCuenta(), cuentaUsuario.getSaldoDisponible() - saldoEgreso);
                } catch (EgresoException ee) {
                    model.addAttribute("egresoError", ee.getMessage());
                    return "egreso/egreso";
                }
            }
        }
        return "redirect:/index";
    }

    @GetMapping("/detallesEgreso/{idEgreso}")
    public String detallesEgreso(@PathVariable("idEgreso") Integer idEgreso, Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if (idEgreso != 0) {

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

            var detalleEgreso = egresoService.buscarEgresoPorIdEgreso(idEgreso);
            model.addAttribute("detalleEgreso", detalleEgreso);
        }
        return "egreso/detallesEgreso";
    }

    @GetMapping("/eliminarEgreso/{idEgreso}")
    public String eliminarEgreso(@PathVariable("idEgreso") Integer idEgreso, Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if (idEgreso != 0) {
            egresoService.actualizarEstadoEgreso(idEgreso);
        }
        return "redirect:/index";
    }

    @GetMapping("/egresos")
    public String egresos(Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if ((Integer) model.getAttribute("idCuenta") != 0) {
            var egresos = egresoService.listarTodosLosEgresos((Integer) model.getAttribute("idCuenta"));
            model.addAttribute("egresos", egresos);

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
        return "egreso/listaEgresos";
    }

    @GetMapping("/envioDinero")
    public String envioDinero(Model model) {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        return "dinero/envioDinero";
    }

    @PostMapping("/enviarDinero")
    public String enviarDinero(Model model, @RequestParam("numeroCuenta") String numeroCuenta,
            @RequestParam("saldoEnviar") double saldoEnviar,
            @RequestParam("estadoEnvio") String estadoEnvio) throws ParseException {

        if (model.getAttribute("idLogin") == null || model.getAttribute("idCuenta") == null) {
            return "redirect:/";
        }

        if (!numeroCuenta.equals("")) {

            var cuentaEnvioDinero = cuentaService.buscarCuentaPorId((Integer) model.getAttribute("idCuenta"));

            if (numeroCuenta.equals(cuentaEnvioDinero.getNumeroCuenta())) {
                model.addAttribute("cuentaIgual", "No se puede enviar dinero a su propia cuenta.");
                return "dinero/envioDinero";
            }
            Fecha fecha = new Fecha();
            try {
                var cuentaEncontrada = cuentaService.buscarCuentaPorNumeroCuenta(numeroCuenta);

                if (model.getAttribute("idCuenta") != null) {

                    if (cuentaEncontrada != null) {

                        try {
                            if (saldoEnviar <= cuentaEnvioDinero.getSaldoDisponible()) {
                                egresoService.insertarEgreso(saldoEnviar, "Usted ha enviado dinero a "
                                        + cuentaEncontrada.getLogin().getUsuario().getNombre() + " " + cuentaEncontrada.getLogin().getUsuario().getApellido(), fecha.getFechaActual(), estadoEnvio, cuentaEnvioDinero);

                                cuentaService.actualizarSaldoCuenta((Integer) model.getAttribute("idCuenta"), cuentaEnvioDinero.getSaldoDisponible() - saldoEnviar);

                                try {
                                    ingresoService.insertarIngreso(saldoEnviar, "Usted ha recibido dinero de: "
                                            + cuentaEnvioDinero.getLogin().getUsuario().getNombre() + " " + cuentaEnvioDinero.getLogin().getUsuario().getApellido(), fecha.getFechaActual(), cuentaEncontrada, estadoEnvio);

                                    cuentaService.actualizarSaldoCuenta(cuentaEncontrada.getIdCuenta(), cuentaEncontrada.getSaldoDisponible() + saldoEnviar);
                                } catch (IngresoException ie) {
                                    model.addAttribute("errorIngreso", ie.getMessage());
                                    return "dinero/envioDinero";
                                }
                            } else {
                                model.addAttribute("insuficienteDinero", "No tiene suficiente dinero para enviar.");
                                return "dinero/envioDinero";
                            }
                        } catch (EgresoException ee) {
                            model.addAttribute("errorEgreso", ee.getMessage());
                            return "dinero/envioDinero";
                        }
                    }
                }
            } catch (CuentaNotFoundException ce) {
                model.addAttribute("cuentaNoEncontrada", ce.getMessage());
                return "dinero/envioDinero";
            }
        } else {
            return "redirect:/envioDinero";
        }
        return "redirect:/index";
    }
}
