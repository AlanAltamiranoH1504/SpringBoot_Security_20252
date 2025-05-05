package altamirano.hernandez.app_springsecurity_2025.security;

import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import altamirano.hernandez.app_springsecurity_2025.repositories.IAutorizarRepository;
import altamirano.hernandez.app_springsecurity_2025.services.Interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplUserDetailsService implements UserDetailsService {
    @Autowired
    IUsuarioService iUsuarioService;
    @Autowired
    IAutorizarRepository iAutorizarRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = iUsuarioService.findByEmail(email);

        //Verificamos existencia de usuario
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        //Configuracion de Permisos
        List<GrantedAuthority> permisos = new ArrayList<>();
        List<String> permisosString = iAutorizarRepository.getPermisosByUserId(usuario.getId());
        for (var rol : permisosString){
            permisos.add(new SimpleGrantedAuthority(rol));
        }
        if (permisos.isEmpty()){
            throw new UsernameNotFoundException("Usuario sin ningun tipo de permisos");
        }
        System.out.println("PERMISOS DEL USUARIO: " + permisos);
        return new User(usuario.getCorreo(), usuario.getPassword(), true, true, true, true, permisos);
    }
}
