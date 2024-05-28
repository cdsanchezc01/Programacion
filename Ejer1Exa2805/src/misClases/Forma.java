package misClases;

import java.awt.Color;

public abstract class Forma {

	private Color colorFondo;
	private Color colorPerimetro;

	public Forma() {
		this.colorFondo = new Color(0xFFFFFF);
		this.colorPerimetro = new Color(0xFFFFFF);
	}

	public Forma(Color colorFondo, Color colorPerimetro) {
		this.colorFondo = colorFondo;
		this.colorPerimetro = colorPerimetro;
	}

	public Color getColorFondo() {
		return colorFondo;
	}

	public void setColorFondo(Color colorFondo) {
		this.colorFondo = colorFondo;
	}

	public Color getColorPerimetro() {
		return colorPerimetro;
	}

	public void setColorPerimetro(Color colorPerimetro) {
		this.colorPerimetro = colorPerimetro;
	}

	abstract void pintar();

	abstract void pintar(Color colorPerimetro);

	void pintar(Color colorPerimetro, Color colorFondo) {
	}

	abstract void rellenar();

	abstract void rellenar(Color colorFondo);
}
