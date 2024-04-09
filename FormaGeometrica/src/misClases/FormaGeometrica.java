package misClases;

public abstract class FormaGeometrica {

	int numLados;

	public FormaGeometrica(int numLados) {
		this.numLados = numLados;
	}
	
	int getNumLados() {
		return numLados;
		
	}

	abstract float calcularPerimetro();

	abstract float calcularArea();

}
