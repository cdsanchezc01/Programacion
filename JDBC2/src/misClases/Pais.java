package misClases;

public class Pais {

	private String ciudad;
	private String pais;
	
	Pais() {
		
	}
	
	public Pais(String ciudad, String pais) {
		this.ciudad = ciudad;
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	
	
}
