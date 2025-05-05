package altamirano.hernandez.app_springsecurity_2025.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/protegido")
public class ProtegidoController {

    @GetMapping("")
    public String protegidoHome(){
        return "protegido/home";
    }

    @GetMapping("/protegido2")
    public String protegido2(){
        return "protegido/protegido2";
    }
}
