package altamirano.hernandez.app_springsecurity_2025.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

//Clase de Configuracion de SpringSecurity
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    //Metodo authenticationManager
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Metodo BCrypPasswordEncode
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Metodo SecurityContextRepository para persistencia de sesion en AJAX
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    //Metodo SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityContext(context -> context
                        .securityContextRepository(securityContextRepository()))
                .authorizeHttpRequests(auth -> auth
                        //Rutas que no requieren autenticacion
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/liberado/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/acceso/registro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/acceso/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        //Liberacion de archivos estaticos
                        .requestMatchers("/css/**", "/js/**", "/imgs/**", "/static/**").permitAll()

                        //Rutas que requieren proteccion (autenticacion o por roles)
                        .requestMatchers(HttpMethod.GET, "/protegido/**").hasAuthority("ADMIN")

                        //Configuraciones Generales
                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
                .formLogin(form -> form
                                .loginPage("/acceso/login") //Ruta de formulario de login
//                                .usernameParameter("email") //Input de email
//                                .passwordParameter("password") //Input de password
//                                .defaultSuccessUrl("/") //Ruta de redireccion si el login es exitoso
//                        .failureUrl("/acceso/login/error") //Ruta de redireccion el el login es erroneo
                                .permitAll()
                )
                .logout(Customizer.withDefaults());
        return http.build();
    }
}
