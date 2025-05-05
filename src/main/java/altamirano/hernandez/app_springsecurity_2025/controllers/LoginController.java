package altamirano.hernandez.app_springsecurity_2025.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acceso")
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken);
        return "login/login";
    }
}
