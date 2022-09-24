package palma.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import palma.app.model.Escuela;
import palma.app.response.EscuelaResponseRest;
import palma.app.service.IEscuelaService;

@RestController
@RequestMapping("/api")
public class EscuelaRestController {

    @Autowired
    private IEscuelaService service;

    @GetMapping("/escuelas")
    public ResponseEntity<EscuelaResponseRest> consultaEscuela(){
        ResponseEntity<EscuelaResponseRest> response = service.buscarEscuelas();
        return response;

    }

    @GetMapping("/escuelas/{id}")
    public ResponseEntity<EscuelaResponseRest> buscarPorId(@PathVariable Long id){
        ResponseEntity<EscuelaResponseRest> response = service.buscarEscuelaPorId(id);
		return response;
	}

    @PostMapping("/escuelas")
	public ResponseEntity<EscuelaResponseRest> crearEscuela(@RequestBody Escuela escuela) {
		ResponseEntity<EscuelaResponseRest> response = service.crearEscuela(escuela);
		return response;
	}

    @PutMapping("/escuelas/{id}")
	public ResponseEntity<EscuelaResponseRest> actualizarEscuela (@RequestBody Escuela escuela, @PathVariable Long id) {
		ResponseEntity<EscuelaResponseRest> response = service.actualizarEscuela(escuela, id);
		return response;
	}
    
    @DeleteMapping("/escuelas/{id}")
	public ResponseEntity<EscuelaResponseRest> eliminarEscuela (@PathVariable Long id) {
		ResponseEntity<EscuelaResponseRest> response = service.eliminarEscuela(id);
		return response;
	}

}
