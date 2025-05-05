package altamirano.hernandez.app_springsecurity_2025.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/liberado")
public class LiberadoController {

    @GetMapping("")
    public String liberadoHome(){
        return "liberado/home";
    }
    @GetMapping("/liberado2")
    public String liberado2(){
        return "liberado/liberado2";
    }
}
