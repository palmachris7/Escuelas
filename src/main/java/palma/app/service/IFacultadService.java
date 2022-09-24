package palma.app.service;

import org.springframework.http.ResponseEntity;

import palma.app.model.Facultad;
import palma.app.response.FacultadResponseRest;

public interface IFacultadService {
    public ResponseEntity<FacultadResponseRest>  buscarFacultades();

    public ResponseEntity<FacultadResponseRest> buscarFacultadPorId(Long id);

    public ResponseEntity<FacultadResponseRest> crearFacultad(Facultad facultad);

    public ResponseEntity<FacultadResponseRest> actualizarFacultad(Facultad facultad, Long id);

    public ResponseEntity<FacultadResponseRest> eliminarFacultad(Long id);
}
