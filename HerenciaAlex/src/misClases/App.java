package misClases;

public class App {

	public static void main(String[] args) {
		Policia poli = new Policia(29, 1.23, "Spain", "Negro",Sexo.HOMBRE, 1200, 30);
		Profesor profe = new Profesor(29, 1.23, "Spain", "Negro",Sexo.MUJER, 1200, 30);
		Profesor profe2 = new Profesor(29, 1.23, "Spain", "Blanco",Sexo.HOMBRE, 1200, 30);

		poli.cobra();
		poli.leMide();
		profe.cobra();
		profe.leMide();
		profe2.cobra();
		profe2.leMide();
		
		
		
	}

}