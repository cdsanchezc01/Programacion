package misClases;

public class Persona {
	private int edad;
	private double altura;
	private String nacion;
	private String color;
	private Sexo tipoSexo=Sexo.HOMBRE;
	private double salario;
	private double longitud;

	public Persona(int edad, double altura, String nacion, String color, Sexo tipoSexo, double salario,
			double longitud) {
		super();
		this.edad = edad;
		this.altura = altura;
		this.nacion = nacion;
		this.color = color;
		this.tipoSexo = tipoSexo;
		this.salario = salario;
		this.longitud = longitud;
	}
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public Sexo getTipoSexo() {
		return tipoSexo;
	}

	public void setTipoSexo(Sexo tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getNacion() {
		return nacion;
	}

	public void setNacion(String nacion) {
		this.nacion = nacion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}