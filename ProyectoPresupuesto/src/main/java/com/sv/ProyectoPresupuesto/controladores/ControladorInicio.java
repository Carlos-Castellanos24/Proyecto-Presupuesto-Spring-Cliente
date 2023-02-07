package com.sv.ProyectoPresupuesto.controladores;

import com.sv.ProyectoPresupuesto.servicio.CuentaService;
import com.sv.ProyectoPresupuesto.servicio.EgresoService;
import com.sv.ProyectoPresupuesto.servicio.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"idLogin", "idCuenta"})
public class ControladorInicio {   
    
    @Autowired
    private CuentaService cuentaService;
    
    @Autowired
    private IngresoService ingresoService;
    
    @Autowired
    private EgresoService egresoService;
    
    @GetMapping("/")
    public String login(){
        return "login";
    }
    
    @GetMapping("/index")
    public String index(Model model){  
        
        if (model.getAttribute("idLogin") == null) {
            return "redirect:/";
        }
        
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
        
        return "index";
    }        
    
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }   
}
