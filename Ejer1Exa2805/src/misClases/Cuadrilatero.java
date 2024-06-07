package misClases;

import java.awt.Color;

class Cuadrilatero extends Forma {
	private Coordenada[] puntos = new Coordenada[4];

	public Cuadrilatero(Coordenada p1, Coordenada p2, Coordenada p3, Coordenada p4) {
		this.puntos[0] = p1;
		this.puntos[1] = p2;
		this.puntos[2] = p3;
		this.puntos[3] = p4;
	}

	public Coordenada[] getCoordenadas() {
		return puntos;
	}

	public void setCoordenadas(Coordenada p1, Coordenada p2, Coordenada p3, Coordenada p4) {
		this.puntos[0] = p1;
		this.puntos[1] = p2;
		this.puntos[2] = p3;
		this.puntos[3] = p4;
	}

	@Override
	void pintar() {
		System.out.println("Pintando el Cuadrilátero con colores por defecto");
	}

	@Override
	void pintar(Color colorPerimetro) {
		setColorPerimetro(colorPerimetro);
		System.out.println("Pintando el Cuadrilátero con color de perímetro: " + colorPerimetro);
	}

	@Override
	void pintar(Color colorPerimetro, Color colorFondo) {
		setColorPerimetro(colorPerimetro);
		setColorFondo(colorFondo);
		System.out.println("Pintando el Cuadrilátero con color de perímetro: " + colorPerimetro + " y color de fondo: " 
				+ colorFondo);
	}

	@Override
	void rellenar() {
		System.out.println("Rellenado de  Cuadrilátero con color por defecto");
	}

	@Override
	void rellenar(Color colorFondo) {
		setColorFondo(colorFondo);
		System.out.println("Rellenado de Cuadrilátero con color de fondo: " + colorFondo);
	}
}
