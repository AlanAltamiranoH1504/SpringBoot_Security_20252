package altamirano.hernandez.app_springsecurity_2025.controllers;

import altamirano.hernandez.app_springsecurity_2025.models.Login;
import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import altamirano.hernandez.app_springsecurity_2025.services.Interfaces.IUsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuhtController {
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    ResponseEntity<?>login(@Valid @RequestBody Login login, BindingResult bindingResult, HttpServletRequest request){
        Map<String, Object> json = new HashMap<>();

        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }
        try{
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            //Establecemos autenticacion manual
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
            request.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
            json.put("status", HttpStatus.OK.value());

            return ResponseEntity.ok().body(json);
        } catch (BadCredentialsException e){
            json.put("status", HttpStatus.UNAUTHORIZED.value());
            json.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody Usuario usuario, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }
        try{
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            iUsuarioService.save(usuario);
            json.put("status", HttpStatus.CREATED.value());
            json.put("msg", "Usuario creado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
}
