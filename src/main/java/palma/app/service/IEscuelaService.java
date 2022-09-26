package palma.app.service;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.ResponseEntity;


import palma.app.model.Escuela;
import palma.app.model.Facultad;
import palma.app.model.Reporte;
import palma.app.response.EscuelaResponseRest;
import net.sf.jasperreports.engine.JRException;

public interface IEscuelaService {
    public ResponseEntity<EscuelaResponseRest>  buscarEscuelas();

    public ResponseEntity<EscuelaResponseRest> buscarEscuelaPorId(Long id);

    public ResponseEntity<EscuelaResponseRest> crearEscuela(Escuela escuela);

    public ResponseEntity<EscuelaResponseRest> actualizarEscuela(Escuela escuela, Long id);

    public ResponseEntity<EscuelaResponseRest> eliminarEscuela(Long id);

    public Reporte obtenerReporteEscuela(Map<String, Object> params) throws JRException, IOException, SQLException;

    public List<Facultad> listarFacultad();
}
