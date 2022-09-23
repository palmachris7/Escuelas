package palma.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import palma.app.response.EscuelaResponseRest;
import palma.app.service.IEscuelaService;

@RestController
@RequestMapping("/api")
public class EscuelaRestController {
    @Autowired
    private IEscuelaService service;
    @GetMapping("/escuelas")
    public EscuelaResponseRest consultaEscuela(){
        EscuelaResponseRest response = service.buscarEscuelas();
        return response;

    }
}
