package altamirano.hernandez.app_springsecurity_2025.repositories;

import altamirano.hernandez.app_springsecurity_2025.models.Autorizar;
import ch.qos.logback.core.model.INamedModel;
import org.springframework.data.repository.CrudRepository;

public interface IAutorizarRepository extends CrudRepository<Autorizar, Integer> {
}
