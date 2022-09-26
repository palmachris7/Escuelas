package palma.app.consumer;

import java.util.Arrays;

public class Response {
	
	private MetadataResponse[] metadata;
	
	EscuelaResponse escuelaResponse = new EscuelaResponse();
	
	public MetadataResponse[] getMetadata() {
		return metadata;
	}
	public void setMetadata(MetadataResponse[] metadata) {
		this.metadata = metadata;
	}
	public EscuelaResponse getEscuelaResponse() {
		return escuelaResponse;
	}
	public void setEscuelaResponse(EscuelaResponse escuelaResponse) {
		this.escuelaResponse = escuelaResponse;
	}
	@Override
	public String toString() {
		return "Response [metadata=" + Arrays.toString(metadata) + ", escuelaResponse=" + escuelaResponse + "]";
	}	
}