package palma.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import palma.app.model.Facultad;

public interface IFacultadDao extends CrudRepository<Facultad,Long> {
    
}
