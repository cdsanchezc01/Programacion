package misClases;

public class Circulo extends FormaGeometrica {

	float radio;
	final float PI = (float) 3.1416;

	public Circulo(float radio) {
		super(0);
		this.radio = radio;

	}

	@Override
	float calcularPerimetro() {
		return 2 * PI * radio;
	}

	@Override
	float calcularArea() {
		return (float) Math.pow(radio * PI, 2);
	}

}
