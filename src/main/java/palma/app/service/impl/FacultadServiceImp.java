package palma.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import palma.app.model.Facultad;
import palma.app.model.dao.IFacultadDao;
import palma.app.response.FacultadResponseRest;
import palma.app.service.IFacultadService;

@Service
public class FacultadServiceImp implements IFacultadService {

    private static final Logger log = LoggerFactory.getLogger(FacultadServiceImp.class);

    @Autowired
    private IFacultadDao facultadDao;
    
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FacultadResponseRest> buscarFacultades() {
        log.info("Inicio Metodo buscarFacultades()");
        FacultadResponseRest response = new FacultadResponseRest();
        try {
            List<Facultad> facultad = (List<Facultad>) facultadDao.findAll();
            response.getFacultadResponse().setFacultad(facultad);
            response.setMetadata("Ok", "200", "Consulta Exitosa");
        } catch (Exception e) {
            response.setMetadata("No ok", "-1", "Error al consultar las facultades");
            log.error("Error al consultar escuelas: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FacultadResponseRest> buscarFacultadPorId(Long id) {
        log.info("Inicio Metodo buscarEscuelaPorId()");
        FacultadResponseRest response = new FacultadResponseRest();
        List<Facultad> facultads = new ArrayList<>();
        try {
            Optional<Facultad> facultad = facultadDao.findById(id);
            if (facultad.isPresent()) {
                facultads.add(facultad.get());
                response.getFacultadResponse().setFacultad(facultads);
            } else {
                response.setMetadata("Respuesta failed", "404", "Facultad no encontrada");
                return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("No ok", "-1", "Error al consultar la facultad");
            log.error("Error al consultar escuelas: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Ok", "200", "Consulta Exitosa");
        return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FacultadResponseRest> crearFacultad(Facultad facultad) {
        FacultadResponseRest response = new FacultadResponseRest();
		List<Facultad> facultads = new ArrayList<>();
		
		try {
			Facultad facultadGuardada = facultadDao.save(facultad);
			
			if(facultadGuardada != null) {
				facultads.add(facultadGuardada);
				response.getFacultadResponse().setFacultad(facultads);
			}else {
				response.setMetadata("Respuesta failed", "404", "Facultad no guardada");
				return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo guardar la facultad");
			return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		response.setMetadata("Respuesta ok", "200", "Facultad creada Correctamente");
		return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<FacultadResponseRest> actualizarFacultad(Facultad facultad, Long id) {
        log.info("Inicio Metodo actualizarEscuela()");
        FacultadResponseRest response = new FacultadResponseRest();
        List<Facultad> facultads = new ArrayList<>();
        try {
            Optional<Facultad> facultadBuscada = facultadDao.findById(id);
            if(facultadBuscada.isPresent()) {
				facultadBuscada.get().setDescFacultad(facultad.getDescFacultad());
				facultadBuscada.get().setFechaRegistro(facultad.getFechaRegistro());
				
				Facultad escuelaActualizar = facultadDao.save(facultadBuscada.get());
				
				if(escuelaActualizar != null) {
					response.setMetadata("Respuesta ok", "200", "Facultad Actualizada");
					facultads.add(escuelaActualizar);
					response.getFacultadResponse().setFacultad(facultads);
				} else {
                    log.error("Error en actualizar la Facultad");
					response.setMetadata("Respuesta failed", "-1", "Facultad no Actualizada");
					return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
                log.error("Error en actualizar la Facultad");
				response.setMetadata("Respuesta nok", "-1", "No existe la Facultad a actualizar");
				return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
        } catch (Exception e) {
            log.error("Error en actualizar la Facultad",e.getMessage());
            e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo actualizar la Facultad");
			return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FacultadResponseRest> eliminarFacultad(Long id) {
        log.info("Inicio Metodo eliminarFacultad()");
        FacultadResponseRest response = new FacultadResponseRest();
		
		try {
			facultadDao.deleteById(id);
			response.setMetadata("Respuesta ok", "200", "Facultad ELiminada");
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo eliminar la Facultad");
			return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.OK);
    }
    
    
}
