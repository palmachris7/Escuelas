package palma.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import palma.app.model.Usuario;



public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByNombreUsuario(String nombreUsuario);
}
