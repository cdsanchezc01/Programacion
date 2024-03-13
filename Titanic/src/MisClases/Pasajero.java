package MisClases;
public class Pasajero {

	boolean superviviente;
	String nombre;
	int clase;
	int edad;
	String sexo;
	
	Pasajero(boolean superviviente, String nombre, int clase, int edad, String sexo){
		this.superviviente=superviviente;
		this.clase=clase;
		this.edad=edad;
		this.sexo=sexo;
		this.nombre=nombre;
	}
	
	@Override
	public String toString() {
		return " Nombre : " + nombre + "\n" + " Edad : " + edad + "\n" + " Clase : " + clase + "\n" + " Sexo : " + sexo + "\n";
	}
}
