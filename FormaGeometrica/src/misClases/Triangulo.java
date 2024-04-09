package misClases;

public class Triangulo extends FormaGeometrica {

	float lado1;
	float lado2;
	float lado3;

	public Triangulo(float lado1, float lado2, float lado3) {
		super(3);
		this.lado1 = lado1;
		this.lado2 = lado2;
		this.lado3 = lado3;

	}

	@Override
	float calcularPerimetro() {
		return lado1 + lado2 + lado3;
	}

	@Override
	float calcularArea() {
//		Aplicamos formula de Heron para calcular el area

		float s = calcularPerimetro() / 2;
		return (float) Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3));
	}

}
