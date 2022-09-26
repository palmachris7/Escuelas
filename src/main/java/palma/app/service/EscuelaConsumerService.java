package palma.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import palma.app.consumer.Response;
import palma.app.model.Escuela;
import palma.app.model.Facultad;

@Service
public class EscuelaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(EscuelaConsumerService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resource.escuelas}")
    private String resource;
    
    @Value("${resource.escuelas}/{id}")
    private String idResource;

    public List<Escuela> listadoEScuelas(){
        ResponseEntity<Response> exchangEntity = restTemplate.exchange(resource, HttpMethod.GET, null, Response.class);
        Response dto = exchangEntity.getBody();
        log.info(dto.getEscuelaResponse().getEscuelas().toString());
        return dto.getEscuelaResponse().getEscuelas();
    }
    public List<Facultad> listadoFacultades(){
        return Arrays.stream(restTemplate.getForObject(resource+"/facultades", Facultad[].class)).collect(Collectors.toList());
    }
}
