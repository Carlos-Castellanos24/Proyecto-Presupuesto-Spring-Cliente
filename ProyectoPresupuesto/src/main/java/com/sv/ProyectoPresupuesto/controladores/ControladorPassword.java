package com.sv.ProyectoPresupuesto.controladores;

import com.sv.ProyectoPresupuesto.Security.Utility;
import com.sv.ProyectoPresupuesto.clases.Login;
import com.sv.ProyectoPresupuesto.exceptions.LoginNotFoundException;
import com.sv.ProyectoPresupuesto.servicio.LoginService;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorPassword {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/olvidarPassword")
    public String olvidarPassword(Model model) {
        model.addAttribute("titulo", "Olvide mi contraseña");
        return "password/restablecerPassword";
    }

    @PostMapping("/enviarMensajePassword")
    public String enviarMensajePassword(HttpServletRequest req, Model model) {
        String correo = req.getParameter("correo");
        String token = RandomString.make(60);

        try {
            loginService.restablecerPassword(token, correo);
            String linkRestablecerPassword = Utility.url(req) + "/restablecerPassword?token=" + token;
            try {
                this.sendEmail(correo, linkRestablecerPassword);
                model.addAttribute("mensajeEnviado", "El mensaje se ha enviado.");
            } catch (MessagingException | UnsupportedEncodingException e) {
                model.addAttribute("errorMensaje", "Error al enviar el mensaje.");
            }
        } catch (LoginNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        }

        return "password/restablecerPassword";
    }

    @GetMapping("/restablecerPassword")
    public String restablecerPassword(@Param(value = "token") String token, Model model) {
        Login login = loginService.get(token);
        if (login == null) {
            model.addAttribute("mensaje", "Codigo Invalido");
            return "restablecerPassword";
        }
        model.addAttribute("token", token);
        return "password/actualizarPassword";
    }

    @PostMapping("/actualizarNuevaPassword")
    public String actualizarNuevaPassword(HttpServletRequest req, Model model) {
        String token = req.getParameter("token");
        String clave = req.getParameter("clave");
        
        Login login = loginService.get(token);
        if (login == null) {
            model.addAttribute("mensaje", "Codigo Invalido");
        }else{
            loginService.actualizarPassword(login, clave);
            model.addAttribute("mensajePassword", "La contraseña ha sido actualizada.");
        }
        return "redirect:/";
    }

    private void sendEmail(String correo, String linkRestablecerPassword) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        helper.setFrom("proyectopresupuesto@outlook.com", "Proyecto Presupuesto");
        helper.setTo(correo);
        String asunto = "Este es tu link para restablecer tu contraseña";

        String contenido = "<p>Hola,</p>"
                + "<p>Has hecho una peticion para restablecer tu contraseña.</p>"
                + "<p>Has click en el link de abajo para poder cambiarla.</p>"
                + "<p><b><a href=\" " + linkRestablecerPassword + "\">Restablecer Contraseña</a></b></p>"
                + "<p>Si no has sido tu, ignora el mensaje.</p>";
        helper.setSubject(asunto);
        helper.setText(contenido, true);

        mailSender.send(mensaje);
    }
}
