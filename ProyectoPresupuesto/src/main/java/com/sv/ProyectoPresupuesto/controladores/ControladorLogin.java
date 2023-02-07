package com.sv.ProyectoPresupuesto.controladores;

import com.sv.ProyectoPresupuesto.servicio.CuentaService;
import com.sv.ProyectoPresupuesto.servicio.LoginService;
import com.sv.ProyectoPresupuesto.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.context.request.WebRequest;

@Controller
@SessionAttributes("idLogin")
public class ControladorLogin {
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private CuentaService cuentaService;
    
    @PostMapping("/validarLogin")
    public String validarLogin(String correo, String clave, Model model) {        
        
        var login = loginService.validarLogin(correo);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if (login != null && encoder.matches(clave, login.getClave())) {

            var usuario = usuarioService.buscarUsuarioPorId(login.getUsuario().getIdUsuario());

            if (usuario != null && usuario.getEstadoUsuario().equals("A")) {
                
                var cuenta = cuentaService.buscarCuentaPorIdLogin(login.getIdLogin());
                if (cuenta.getSaldoDisponible() < 0.0) {
                    model.addAttribute("errorCuenta", "Hay un problema con su cuenta, favor de intentar mas tarde.");
                    return "login";
                }
                
                model.addAttribute("idLogin", login.getIdLogin());
                return "redirect:/index";
            }
        }
        return "redirect:/";
    }
    
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(WebRequest request, DefaultSessionAttributeStore status, ModelMap model) {
        
        if (model.getAttribute("idLogin") != null) {   
            
            model.remove("idLogin");
            status.cleanupAttribute(request, "idLogin");   
            
            return "redirect:/";
        }
        return "redirect:/";
    }
}
