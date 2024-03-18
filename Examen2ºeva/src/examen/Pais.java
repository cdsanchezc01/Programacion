package examen;

public class Pais implements Comparable<Pais> {

	private String nombre;
	private String continente;
	private String capital;
	private int poblacion;
	private float superficie;

	Pais(String nombre, String continente, String capital, int poblacion, float superficie) {
		this.nombre = nombre;
		this.continente = continente;
		this.capital = capital;
		this.poblacion = poblacion;
		this.superficie = superficie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public float getSuperficie() {
		return superficie;
	}

	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}

	@Override
	public int compareTo(Pais o) {

		if (Aplicacion.tipoOrdenacion == 1) {
			Integer poblacion1 = this.poblacion;
			Integer poblacion2 = o.poblacion;

			return -poblacion1.compareTo(poblacion2);
		} else if (Aplicacion.tipoOrdenacion == 2) {
			Float superficie1 = this.superficie;
			Float superficie2 = o.superficie;

			return -superficie1.compareTo(superficie2);
		}

		return 0;
	}

}