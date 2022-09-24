package palma.app.service;

import org.springframework.http.ResponseEntity;

import palma.app.model.Escuela;
import palma.app.response.EscuelaResponseRest;

public interface IEscuelaService {
    public ResponseEntity<EscuelaResponseRest>  buscarEscuelas();

    public ResponseEntity<EscuelaResponseRest> buscarEscuelaPorId(Long id);

    public ResponseEntity<EscuelaResponseRest> crearEscuela(Escuela escuela);

    public ResponseEntity<EscuelaResponseRest> actualizarEscuela(Escuela escuela, Long id);

    public ResponseEntity<EscuelaResponseRest> eliminarEscuela(Long id);
}
