package palma.app.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.sf.jasperreports.engine.JRException;
import palma.app.jasper.JasperReportManager;
import palma.app.model.Escuela;
import palma.app.model.Facultad;
import palma.app.model.Reporte;
import palma.app.model.dao.IEscuelaDao;
import palma.app.model.dao.IFacultadDao;
import palma.app.response.EscuelaResponseRest;
import palma.app.service.IEscuelaService;

@Service
public class EscuelaServiceImp implements IEscuelaService {
    private static final Logger log = LoggerFactory.getLogger(EscuelaServiceImp.class);

    @Autowired
    private IEscuelaDao escuelaDao;
    
    @Autowired
    private IFacultadDao facultadDao;

    @Autowired
	private JasperReportManager reportManager;

    @Autowired
	private DataSource dataSource;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EscuelaResponseRest> buscarEscuelas() {
        log.info("Inicio Metodo buscarEscuelas()");
        EscuelaResponseRest response = new EscuelaResponseRest();
        try {
            List<Escuela> escuela = (List<Escuela>) escuelaDao.findAll();
            response.getEscuelaResponse().setEscuela(escuela);
            response.setMetadata("Ok", "200", "Consulta Exitosa");
        } catch (Exception e) {
            response.setMetadata("No ok", "-1", "Error al consultar las escuelas");
            log.error("Error al consultar escuelas: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EscuelaResponseRest> buscarEscuelaPorId(Long id) {
        log.info("Inicio Metodo buscarEscuelaPorId()");
        EscuelaResponseRest response = new EscuelaResponseRest();
        List<Escuela> escuelas = new ArrayList<>();
        try {
            Optional<Escuela> escuela = escuelaDao.findById(id);
            if (escuela.isPresent()) {
                escuelas.add(escuela.get());
                response.getEscuelaResponse().setEscuela(escuelas);
            } else {
                response.setMetadata("Respuesta failed", "404", "Escuela no encontrada");
                return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("No ok", "-1", "Error al consultar la escuela");
            log.error("Error al consultar escuelas: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Ok", "200", "Consulta Exitosa");
        return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EscuelaResponseRest> crearEscuela(Escuela escuela) {
        log.info("Inicio Metodo crearEscuela()");
        EscuelaResponseRest response = new EscuelaResponseRest();
		List<Escuela> escuelas = new ArrayList<>();
		
		try {
			Escuela escuelaGuardada = escuelaDao.save(escuela);
			
			if(escuelaGuardada != null) {
				escuelas.add(escuelaGuardada);
				response.getEscuelaResponse().setEscuela(escuelas);
			}else {
                log.error("Error en guardar la escuela ");
				response.setMetadata("Respuesta failed", "-1", "Escuela no guardada");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
            log.error("Error en guardar la escuela",e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "500", "No se pudo guardar la escuela");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		response.setMetadata("Respuesta ok", "200", "Escuela creada Correctamente");
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
      
    }

    @Override
    @Transactional
    public ResponseEntity<EscuelaResponseRest> actualizarEscuela(Escuela escuela, Long id) {
        log.info("Inicio Metodo actualizarEscuela()");
        EscuelaResponseRest response = new EscuelaResponseRest();
        List<Escuela> escuelas = new ArrayList<>();
        try {
            Optional<Escuela> escuelaBuscada = escuelaDao.findById(id);
            if(escuelaBuscada.isPresent()) {
				escuelaBuscada.get().setFacultad(escuela.getFacultad());
				escuelaBuscada.get().setNombre(escuela.getNombre());
                escuelaBuscada.get().setDescripcion(escuela.getDescripcion());
				escuelaBuscada.get().setCantAlumnos(escuela.getCantAlumnos());
				escuelaBuscada.get().setRecursoFiscal(escuela.getRecursoFiscal());
				escuelaBuscada.get().setLicenciada(escuela.getLicenciada());
				escuelaBuscada.get().setCalificacion(escuela.getCalificacion());
				
				Escuela escuelaActualizar = escuelaDao.save(escuelaBuscada.get());
				
				if(escuelaActualizar != null) {
					response.setMetadata("Respuesta ok", "200", "Escuela Actualizada");
					escuelas.add(escuelaActualizar);
					response.getEscuelaResponse().setEscuela(escuelas);
				} else {
                    log.error("Error en actualizar la escuela");
					response.setMetadata("Respuesta failed", "-1", "Escuela no Actualizada");
					return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
                log.error("Error en actualizar la escuela");
				response.setMetadata("Respuesta nok", "-1", "No existe la escuela a actualizar");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
        } catch (Exception e) {
            log.error("Error en actualizar la escuela",e.getMessage());
            e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo actualizar la escuela");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<EscuelaResponseRest> eliminarEscuela(Long id) {
        log.info("Inicio Metodo eliminarEscuela()");
        EscuelaResponseRest response = new EscuelaResponseRest();
		
		try {
			escuelaDao.deleteById(id);
			response.setMetadata("Respuesta ok", "200", "Escuela ELiminada");
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo eliminar la escuela");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
    }

   
    @Override
    public List<Facultad> listarFacultad() {
        return (List<Facultad>) facultadDao.findAll();
    }

    @Override
    public Reporte obtenerReporteEscuela(Map<String, Object> params) throws JRException, IOException, SQLException {
        String filename= "reporte_escuelas";
		Reporte report = new Reporte();
		report.setFileName(filename + ".pdf");
		
		ByteArrayOutputStream stream = reportManager.export(filename, params, dataSource.getConnection());
		
		byte[] bs = stream.toByteArray();		
		report.setStream(new ByteArrayInputStream(bs));
		report.setLength(bs.length);
		
		return report;
        
    }

    
}
