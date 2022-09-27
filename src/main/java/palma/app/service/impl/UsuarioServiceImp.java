package palma.app.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import palma.app.model.Usuario;
import palma.app.model.dao.IUsuariosDao;
import palma.app.service.IUsuariosService;
@Service
public class UsuarioServiceImp implements IUsuariosService {
    @Autowired
	private IUsuariosDao usuariosRepo;
    
    @Override
    public void guardar(Usuario usuario) {
        usuariosRepo.save(usuario);
        
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariosRepo.deleteById(idUsuario);
        
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuariosRepo.findAll();
        
    }

    @Override
    public List<Usuario> buscarRegistrados() {
        return usuariosRepo.findByFecharegistroNotNull();
    }

    @Override
    public Usuario buscarPorId(Integer idUsuario) {
        Optional<Usuario> optional = usuariosRepo.findById(idUsuario);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuariosRepo.findByUsername(username);
    }
    @Transactional
    @Override
    public int bloquear(int idUsuario) {
        int rows = usuariosRepo.lock(idUsuario);
		return rows;
    }
    @Transactional
    @Override
    public int activar(int idUsuario) {
        int rows = usuariosRepo.unlock(idUsuario);
		return rows;
    }

    
}
