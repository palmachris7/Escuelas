package palma.app.model.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import palma.app.model.Usuario;




public interface IUsuariosDao extends JpaRepository<Usuario, Integer>{
    Usuario findByUsername(String username);
	List<Usuario> findByFecharegistroNotNull();
	
	@Modifying
    @Query("UPDATE Usuario SET estatus=0 WHERE idusuario = :paramIdUsuario")
    int lock(@Param("paramIdUsuario") int idUsuario);
	
	@Modifying
    @Query("UPDATE Usuario SET estatus=1 WHERE idusuario = :paramIdUsuario")
    int unlock(@Param("paramIdUsuario") int idUsuario);


	
	
} 
