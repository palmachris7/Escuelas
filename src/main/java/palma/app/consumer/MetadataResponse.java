package palma.app.consumer;

public class MetadataResponse {
	private String tipo;
	private String codigo;
	private String dato;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	@Override
	public String toString() {
		return "Metadata [tipo=" + tipo + ", codigo=" + codigo + ", dato=" + dato + "]";
	}
	
}
