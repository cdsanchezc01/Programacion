package misClases;

import java.awt.Color;

class Triangulo extends Forma {
	private Coordenada[] puntos = new Coordenada[3];

	public Triangulo(Coordenada p1, Coordenada p2, Coordenada p3) {
		this.puntos[0] = p1;
		this.puntos[1] = p2;
		this.puntos[2] = p3;
	}

	public Coordenada[] getCoordenadas() {
		return puntos;
	}

	public void setCoordenadas(Coordenada p1, Coordenada p2, Coordenada p3) {
		this.puntos[0] = p1;
		this.puntos[1] = p2;
		this.puntos[2] = p3;
	}

	@Override
	void pintar() {
		System.out.println("Pintar Triángulo con colores por defecto");
	}

	@Override
	void pintar(Color colorPerimetro) {
		setColorPerimetro(colorPerimetro);
		System.out.println("Pintar Triángulo con color de perímetro: " + colorPerimetro);
	}

	@Override
	void pintar(Color colorPerimetro, Color colorFondo) {
		setColorPerimetro(colorPerimetro);
		setColorFondo(colorFondo);
		System.out.println(
				"Pintar Triángulo con color de perímetro: " + colorPerimetro + " y color de fondo: " + colorFondo);
	}

	@Override
	void rellenar() {
		System.out.println("Rellena el  Triángulo con color por defecto");
	}

	@Override
	void rellenar(Color colorFondo) {
		setColorFondo(colorFondo);
		System.out.println("Rellena el Triángulo con color de fondo: " + colorFondo);
	}

	@Override
	void pintar(Color colorPerimetro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void rellenar(Color colorFondo) {
		// TODO Auto-generated method stub
		
	}
}
