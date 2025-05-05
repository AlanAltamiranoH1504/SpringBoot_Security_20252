package altamirano.hernandez.app_springsecurity_2025.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restringido")
public class RestringidoController {

    @GetMapping("")
    public String restringidoHome(){
        return "restringido/home";
    }

    @GetMapping("/restringido2")
    public String restringido2(){
        return "restringido/restringido2";
    }
}
