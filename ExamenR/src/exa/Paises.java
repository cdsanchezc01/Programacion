package exa;

public class Paises implements Comparable<Paises> {
	private String nombre;
	private String capital;
	private String continente;
	private int poblacion;
	private float superficie;

	Paises(String nombre, String capital, String continente, int poblacion, float superficie) {
		this.capital = capital;
		this.nombre = nombre;
		this.continente = continente;
		this.poblacion = poblacion;
		this.superficie = superficie;
	}

	@Override
	public String toString() {
		return "***************************************************** \n" + nombre + "(" + capital + ")"
				+ "                 " + continente + "\n" + "Población: " + poblacion + "               "
				+ "Superficie: " + superficie + "\n" + "***************************************************** \n";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
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
	public int compareTo(Paises o) {
		// TODO Auto-generated method stub
		if (Aplicacion.tipoOrdenacion == 0) {
			if (this.poblacion > o.poblacion)
				return 1;
			else if (this.poblacion < o.poblacion) {
				return -1;
			}
			return 0;
		} else {
			if (this.superficie > o.superficie)
				return 1;
			else if (this.superficie < o.superficie) {
				return -1;
			}
			return 0;
		}

	}
}
