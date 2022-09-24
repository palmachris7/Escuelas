package palma.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import palma.app.model.Escuela;
import palma.app.model.dao.IEscuelaDao;
import palma.app.response.EscuelaResponseRest;
import palma.app.service.IEscuelaService;

@Service
public class EscuelaServiceImp implements IEscuelaService {
    private static final Logger log = LoggerFactory.getLogger(EscuelaServiceImp.class);

    @Autowired
    private IEscuelaDao escuelaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EscuelaResponseRest> buscarEscuelas() {
        log.info("Inicio Metodo buscarEscuelas()");
        EscuelaResponseRest response = new EscuelaResponseRest();
        try {
            List<Escuela> escuela = (List<Escuela>) escuelaDao.findAll();
            response.getEscuelaResponse().setEscuela(escuela);
            response.setMetadata("Ok", "00", "Consulta Exitosa");
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
                response.setMetadata("Respuesta failed", "-1", "Escuela no encontrada");
                return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("No ok", "-1", "Error al consultar la escuela");
            log.error("Error al consultar escuelas: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Ok", "00", "Consulta Exitosa");
        return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscuelaResponseRest> crearEscuela(Escuela escuela) {
        EscuelaResponseRest response = new EscuelaResponseRest();
		List<Escuela> escuelas = new ArrayList<>();
		
		try {
			Escuela escuelaGuardada = escuelaDao.save(escuela);
			
			if(escuelaGuardada != null) {
				escuelas.add(escuelaGuardada);
				response.getEscuelaResponse().setEscuela(escuelas);
			}else {
				response.setMetadata("Respuesta failed", "-1", "Escuela no guardada");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo guardar la escuela");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		response.setMetadata("Respuesta ok", "1", "Escuela creada Correctamente");
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
      
    }

    
}
