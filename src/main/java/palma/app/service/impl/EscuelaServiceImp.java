package palma.app.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import palma.app.model.Escuela;
import palma.app.model.dao.IEscuelaDao;
import palma.app.response.EscuelaResponseRest;
import palma.app.service.IEscuelaService;

@Service
public class EscuelaServiceImp implements IEscuelaService{
    private static final Logger log = LoggerFactory.getLogger(EscuelaServiceImp.class);

    @Autowired
    private IEscuelaDao escuelaDao;

    @Override
    @Transactional
    public EscuelaResponseRest buscarEscuelas() {
        log.info("Inicio Metodo buscarEscuelas()");
        EscuelaResponseRest response = new EscuelaResponseRest();
        try {
            List<Escuela> escuela= (List<Escuela>) escuelaDao.findAll();
            response.getEscuelaResponse().setEscuela(escuela);
            response.setMetadata("Ok", "00", "Respuesta Exitosa");
        } catch (Exception e) {
            response.setMetadata("No ok", "-1", "Respuesta Incorrecta");
            log.error("Error al consultar escuelas: ", e.getMessage());
            e.getStackTrace();
        }
        return response;
    }
    
}
