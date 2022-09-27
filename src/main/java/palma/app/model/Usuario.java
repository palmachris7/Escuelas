package palma.app.model;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Data;
@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idusuario;

	private String username;
	private String nombre;
	private String email;
	private String password;
	private Integer estatus;
	private Date fecharegistro;	

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UsuarioPerfil", 
			joinColumns = @JoinColumn(name = "idusuario"), 
			inverseJoinColumns = @JoinColumn(name = "idperfil") 
	)
	private List<Perfil> perfiles;

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
	public void agregar(Perfil tempPerfil) {
		if (perfiles == null) {
			perfiles = new LinkedList<>();
		}
		perfiles.add(tempPerfil);
	}

}
