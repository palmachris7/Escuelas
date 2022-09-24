package palma.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name="escuela")
public class Escuela implements Serializable {
    
    private static final long serialVersionUID = -7703691020344717540L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idescuela;

    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
	private Facultad facultad;
    
    @Column(unique=true)
    private String nombre;

    private Integer cantalumnos;

    private String descripcion;

    private Double recursofiscal;

    private Boolean licenciada;

    private Integer calificacion;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date fecharegistro;

}
