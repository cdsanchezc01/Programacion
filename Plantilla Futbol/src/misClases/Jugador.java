package misClases;

public class Jugador extends DatosIntegrante {
	
	int dorsal;
	String posicion;
	int añoComienzo;
	double altura;
	
	public Jugador(int codIdentificador, String nombre, String nacionalidad, String lugarNacimiento,
			String fechaNacimiento, int dorsal, String posicion, int añoComienzo, double altura) {
		super(codIdentificador, nombre,nacionalidad, lugarNacimiento,fechaNacimiento);
		this.dorsal=dorsal;
		this.posicion=posicion;
		this.añoComienzo=añoComienzo;
		this.altura=altura;
	}

}
