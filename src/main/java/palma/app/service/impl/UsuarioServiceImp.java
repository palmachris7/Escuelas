package palma.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import palma.app.model.Role;
import palma.app.model.Usuario;
import palma.app.model.dao.IRolDao;
import palma.app.model.dao.IUsuarioDao;
import palma.app.service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImp.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IRolDao rolDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario userUsuario = usuarioDao.findByNombreUsuario(username);
		if(userUsuario == null) {
			LOGGER.error("No existe el username");
			throw new UsernameNotFoundException("No existe ese usuario en al db");
		}else {
			LOGGER.error("Existe el Usuario con nombre usuario de: {}", username);
		}
		
		List<GrantedAuthority> authorities = userUsuario.getRoles()
				.stream()
				.map( role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek( authority -> LOGGER.info("Role: "+ authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(userUsuario.getNombreUsuario(), userUsuario.getPassword(), authorities);
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		LOGGER.info("Metodo guardar Usuario: "  +usuario.getPassword());
		return usuarioDao.save(usuario);
	}

	@Override
	public Role guardarRole(Role role) {
		return rolDao.save(role);
	}

	@Override
	public void agregarRoleAUsuario(String nombreUsuario, String roleName) {
		Usuario user = usuarioDao.findByNombreUsuario(nombreUsuario);
		Role role =  rolDao.findByNombre(roleName);
		user.getRoles().add(role);	
	}

	@Override
	public Usuario getUsuario(String nombreUsuario) {
		return usuarioDao.findByNombreUsuario(nombreUsuario);
	}

	@Override
	public List<Usuario> listadoUsuario() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	
	public void delete() {
		usuarioDao.deleteAll();
		rolDao.deleteAll();
	}

}
