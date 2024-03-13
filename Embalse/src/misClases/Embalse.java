package misClases;

public class Embalse {
//	Atribus
	private String nombre;
	private int capacidad;
	private int nivelActual;
	private int ultimaMedida;
	private String confederacionHidro;
	private String provincia;
	
	public Embalse( String nombre, int capacidad, int nivelActual, int ultimaMedida, String confederacionHidro, String provincia) {
		this.nombre=nombre;
		this.capacidad=capacidad;
		this.nivelActual=nivelActual;
		this.ultimaMedida=ultimaMedida;
		this.confederacionHidro=confederacionHidro;
		this.provincia=provincia;
	
	}
	
//	Metodos Get And Set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getNivelActual() {
		return nivelActual;
	}
	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}
	public int getUltimaMedida() {
		return ultimaMedida;
	}
	public void setUltimaMedida(int ultimaMedida) {
		this.ultimaMedida = ultimaMedida;
	}
	public String getConfederacionHidro() {
		return confederacionHidro;
	}
	public void setConfederacionHidro(String confederacionHidro) {
		this.confederacionHidro = confederacionHidro;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
































}

