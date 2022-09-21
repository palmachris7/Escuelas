package palma.app.service;

import org.springframework.data.repository.CrudRepository;

import palma.app.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long>{
    
}
