package misClases;

import java.util.ArrayList;

public class GestorEmbalse {

	ArrayList<Embalse> Embalses;

	public GestorEmbalse() {
		Embalses = new ArrayList<Embalse>();

	}
//----------------------------------------------------------------------------
	void AñadirEmbalse() {
		System.out.println(" Introduce el nombre del Embalse ");
		String nombre = App.entrada.nextLine();

		int i = 0;
		boolean enc = false;
		while (i < Embalses.size() && enc == false) {

			if (Embalses.get(i).getNombre().equals(nombre)) {
				enc = true;
			} else
				i++;
		}

		if (enc == false) {

			System.out.println(" Introduce la capacidad ");
			int capacidad = App.entrada.nextInt();
			App.entrada.nextLine();

			System.out.println(" Introduce el nivel actual ");
			int nivelActual = App.entrada.nextInt();
			App.entrada.nextLine();

			System.out.println(" Introduce la última medida ");
			int ultimaMedida = App.entrada.nextInt();
			App.entrada.nextLine();

			System.out.println(" Introduce el nombre de la confederacion Hidrografica ");
			String confederacionHidro = App.entrada.nextLine();

			System.out.println(" Introduce el nombre de la provincia ");
			String provincia = App.entrada.nextLine();
			// Creo el objeto y lo añado , al ArrayList
			Embalse embalse = new Embalse(nombre, capacidad, nivelActual, ultimaMedida, confederacionHidro, provincia);
			Embalses.add(embalse);
		} else
			System.out.println("Ya existe un embalse con ese nombre");

	}
//----------------------------------------------------------------------------------------------------
	
	
	void BorrarEmbalse() {
		System.out.println(" Introduce el nombre del Embalse a borrar ");
		String nombre = App.entrada.nextLine();

		int i = 0;
		boolean enc = false;
		while (i < Embalses.size() && enc == false) {

			if (Embalses.get(i).getNombre().equals(nombre)) {
				enc = true;
			} else
				i++;
		}

		if (enc == false) {
			Embalses.remove(i);
		} else
			System.out.println("Ya existe un embalse con ese nombre");

	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
