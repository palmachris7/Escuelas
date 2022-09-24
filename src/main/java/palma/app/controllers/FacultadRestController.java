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

import palma.app.model.Facultad;
import palma.app.response.FacultadResponseRest;
import palma.app.service.IFacultadService;

@RestController
@RequestMapping("/api")
public class FacultadRestController {
    @Autowired
    private IFacultadService service;

    @GetMapping("/facultades")
    public ResponseEntity<FacultadResponseRest> consultaEscuela(){
        ResponseEntity<FacultadResponseRest> response = service.buscarFacultades();
        return response;
    }

    @GetMapping("/facultades/{id}")
    public ResponseEntity<FacultadResponseRest> buscarPorId(@PathVariable Long id){
        ResponseEntity<FacultadResponseRest> response = service.buscarFacultadPorId(id);
		return response;
	}

    @PostMapping("/facultades")
	public ResponseEntity<FacultadResponseRest> crearEscuela(@RequestBody Facultad facultad) {
		ResponseEntity<FacultadResponseRest> response = service.crearFacultad(facultad);
		return response;
	}

    @PutMapping("/facultades/{id}")
	public ResponseEntity<FacultadResponseRest> actualizarFacultad (@RequestBody Facultad facultad, @PathVariable Long id) {
		ResponseEntity<FacultadResponseRest> response = service.actualizarFacultad(facultad, id);
		return response;
	}
    @DeleteMapping("/facultades/{id}")
	public ResponseEntity<FacultadResponseRest> eliminarFacultad (@PathVariable Long id) {
		ResponseEntity<FacultadResponseRest> response = service.eliminarFacultad(id);
		return response;
	}
}
