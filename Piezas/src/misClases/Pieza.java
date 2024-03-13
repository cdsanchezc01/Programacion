package misClases;

public class Pieza {

	private int codigo;
	private String nombre;
	private String descripcion;
	private float largo;
	private float ancho;
	private float peso;

//------------------------------------------------------------------------------	
	Pieza(int codigo, String nombre, String descripcion, float largo, float ancho, float peso) {

		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.largo = largo;
		this.ancho = ancho;
		this.peso = peso;
	}

//----------------------------------------------------------------------------------	
//	Metodos get and set
	public int getCodPieza() {
		return codigo;
	}

	public void setCodPieza(int codPieza) {
		this.codigo = codPieza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getLargo() {
		return largo;
	}

	public void setLargo(float largo) {
		this.largo = largo;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

//--------------------------------------------------------------------------------------------------	
	@Override
	public String toString() {
		// TODO Apéndice de método generado automáticamente
		return codigo + "-" + nombre + "-" + descripcion + "-" + largo + "-" + ancho + "-" + peso + "-";
	}
//--------------------------------------------------------------------------------------------------------	
}
