package palma.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import palma.app.model.Escuela;

public interface IEscuelaDao extends CrudRepository<Escuela, Long>{
    
}
