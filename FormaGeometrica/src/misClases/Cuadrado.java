package misClases;

public class Cuadrado extends FormaGeometrica {

	float tamLado;

	public Cuadrado(float tamLado) {
		super(4);
		this.tamLado = tamLado;

	}

	@Override
	float calcularPerimetro() {
		return 4 * tamLado;
	}

	@Override
	float calcularArea() {
		return tamLado * tamLado;
	}

}
