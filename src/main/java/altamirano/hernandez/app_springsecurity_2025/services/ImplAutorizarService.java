package altamirano.hernandez.app_springsecurity_2025.services;

import altamirano.hernandez.app_springsecurity_2025.models.Autorizar;
import altamirano.hernandez.app_springsecurity_2025.repositories.IAutorizarRepository;
import altamirano.hernandez.app_springsecurity_2025.services.Interfaces.IAutorizarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplAutorizarService implements IAutorizarService {
    @Autowired
    private IAutorizarRepository iAutorizarRepository;

    @Override
    public List<Autorizar> findAll() {
        List<Autorizar> listaAutorizar = (List<Autorizar>) iAutorizarRepository.findAll();
        return listaAutorizar;
    }

    @Override
    public Autorizar findById(int id) {
        try{
            Autorizar foundedAutorizar = iAutorizarRepository.findById(id).get();
            return foundedAutorizar;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Autorizar autorizar) {
        try {
            iAutorizarRepository.save(autorizar);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try{
            Autorizar foundedAutorizar = iAutorizarRepository.findById(id).get();
            iAutorizarRepository.deleteById(foundedAutorizar.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
