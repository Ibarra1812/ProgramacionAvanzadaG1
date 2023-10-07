package com.tp.ap.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorMain {

    @GetMapping("/")
    public String mostrarPaginaPrincipal() {
        return "index";
    }
}