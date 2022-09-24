package palma.app.response;
import java.util.List;
import palma.app.model.Facultad;

public class FacultadResponse {

    private List<Facultad> facultad;

    public List<Facultad> getFacultad() {
        return facultad;
    }

    public void setFacultad(List<Facultad> facultad) {
        this.facultad = facultad;
    }    
}
