package altamirano.hernandez.app_springsecurity_2025.services;

import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import altamirano.hernandez.app_springsecurity_2025.repositories.IUsuarioRepository;
import altamirano.hernandez.app_springsecurity_2025.services.Interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplUsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = (List<Usuario>) iUsuarioRepository.findAll();
        return usuarios;
    }

    @Override
    public Usuario findById(int id) {
        try{
            Usuario foundedUsuario = iUsuarioRepository.findById(id).get();
            return foundedUsuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        try {
            Usuario foundedUsuario = iUsuarioRepository.findByEmail(email);
            return foundedUsuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Usuario usuario) {
        iUsuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(int id) {
        try{
            Usuario foundUsuario = iUsuarioRepository.findById(id).get();
            iUsuarioRepository.deleteById(foundUsuario.getId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
