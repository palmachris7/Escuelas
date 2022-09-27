package palma.app.service;

import java.util.List;

import palma.app.model.Role;
import palma.app.model.Usuario;



public interface IUsuarioService {
	
	public Usuario guardarUsuario(Usuario usuario);
	
	public Role guardarRole(Role role);
	
	public void agregarRoleAUsuario(String nombreUsuario, String roleName);
	
	public Usuario getUsuario(String nombreUsuario);
	
	public List<Usuario> listadoUsuario();
	
	public void delete();
}
