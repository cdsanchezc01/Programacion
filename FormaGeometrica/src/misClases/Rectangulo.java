package misClases;

public class Rectangulo extends FormaGeometrica {

	float base;
	float altura;

	public Rectangulo(float base, float altura) {
		super(4);
		this.altura = altura;
		this.base = base;

	}

	@Override
	float calcularPerimetro() {
		return (2 * base) + (2 * altura);
	}

	@Override
	float calcularArea() {
		return base * altura;
	}

}
