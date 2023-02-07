package com.sv.ProyectoPresupuesto.Security;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    
    public static String url(HttpServletRequest req){
        String url = req.getRequestURL().toString();
        return url.replace(req.getServletPath(), "");
    }
}
