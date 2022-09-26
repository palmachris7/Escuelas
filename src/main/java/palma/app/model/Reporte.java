package palma.app.model;

import java.io.ByteArrayInputStream;

import lombok.Data;

@Data
public class Reporte {
	
	private String fileName;
	
	private ByteArrayInputStream stream;
	
	private Integer length;

	
	
}
