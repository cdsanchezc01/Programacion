package exa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GestorPaises {
	ArrayList<Paises> lista;

	GestorPaises() {
		lista = new ArrayList<Paises>();

	}

	public void cargarDatosLista(String ruta) throws Exception {

		File fichero = new File(ruta);
		if (fichero.isFile() == true) {
			BufferedReader bfr = new BufferedReader(new FileReader(ruta));
			String linea;
			int numLinea = 0;
			while ((linea = bfr.readLine()) != null) {
				numLinea++;
				if (numLinea != 1) {
					String[] campos = linea.split(";");
					Paises nuevo = new Paises(campos[0], campos[1], campos[2], Integer.parseInt(campos[3]),
							Float.parseFloat(campos[4]));
					lista.add(nuevo);
				}
			}
			bfr.close();

		} else {
			System.out.println("Problemas con el fichero");
		}
	}

	public int buscarPaisNombre() {
		System.out.println("Dime el nombre del país que quieres buscar");
		String nombre = Aplicacion.teclado.nextLine();
		Aplicacion.teclado.nextLine();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase()) == true) {
				return i;
			}
		}
		return -1;
	}

	public int buscarPaisContinente(String continente) {

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getContinente().toUpperCase().equals(continente.toUpperCase()) == true) {
				return i;
			}
		}
		return -1;
	}

	public void mostrarInfo() {
		int opc = buscarPaisNombre();
		if (opc != -1) {
			System.out.println(lista.get(opc));

		} else {
			System.out.println("Ese país no se encuentra en la lista");
		}
	}

	public void mostrarPaisIntervalo() {
		System.out.println("Dime el valor mínimo del intervalo");
		int val = Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();
		System.out.println("Dime el valor máximo del intervalo");
		int val2 = Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();
		ArrayList<Paises> ordenar;
		ordenar = new ArrayList<Paises>();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getPoblacion() > val && lista.get(i).getPoblacion() < val2) {
				ordenar.add(lista.get(i));

			}
		}
		System.out.println("La relación de los paises comprendida entre estos valores es: ");
		for (int i = 0; i < ordenar.size(); i++) {

			System.out.println(ordenar.get(i).getNombre() + "(" + ordenar.get(i).getContinente() + ")" + "             "
					+ "Población: " + ordenar.get(i).getPoblacion());
			System.out.println("");
		}
	}

	public void actualizarPoblación() throws Exception {

		int opc = buscarPaisNombre();
		if (opc != -1) {
			System.out.println("Dime el nuevo valor de la población de este país");
			int pob = Aplicacion.teclado.nextInt();
			Aplicacion.teclado.nextLine();
			lista.get(opc).setPoblacion(pob);
			guardarDatosFichero();
		} else {
			System.out.println("Ese país no se encuentra en la lista");
		}
	}

	public void guardarDatosFichero() throws Exception {
		BufferedWriter fichero = new BufferedWriter(
				new FileWriter("C:\\Users\\usuario\\Desktop\\programacion2\\Paises.csv"));
		fichero.write("País;Capital;Continente;Población;Superficie \n");

		char separador = ';';
		for (int i = 0; i < lista.size(); i++) {
			// Componemos el formato del String para guardarlo en el nuevoFichero
			Paises pas = lista.get(i);
			String registro = pas.getNombre() + separador + pas.getCapital() + separador + pas.getContinente()
					+ separador + pas.getPoblacion() + separador + pas.getSuperficie() + '\n';
			fichero.write(registro);
		}
		fichero.close();
	}

	public void mostrarPaisesPoblacion() {
		System.out.println("Dime el nombre del continente que quieres buscar");
		String nombre = Aplicacion.teclado.nextLine();
		Aplicacion.teclado.nextLine();
		ArrayList<Paises> ordenar;
		ordenar = new ArrayList<Paises>();
		int opc = buscarPaisContinente(nombre);
		if (opc != -1) {
			System.out.println("La relación de países de Europa ordenados por población es: ");
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getContinente().equalsIgnoreCase(nombre) == true) {
					ordenar.add(lista.get(i));
				}
			}
			Collections.sort(ordenar);
			for (int i = 0; i < ordenar.size(); i++) {
				System.out.println(i + ".-" + ordenar.get(i).getNombre() + "           " + "Poblacion: "
						+ ordenar.get(i).getPoblacion());
			}

		} else {
			System.out.println("Ese continente no se encuentra en la lista");
		}
	}

	public void mostrarPaisesSuperficie() {
		Aplicacion.tipoOrdenacion = 1;
		System.out.println("Dime el nombre del continente que quieres buscar");
		String nombre = Aplicacion.teclado.nextLine();
		Aplicacion.teclado.nextLine();
		ArrayList<Paises> ordenar;
		ordenar = new ArrayList<Paises>();
		int opc = buscarPaisContinente(nombre);
		if (opc != -1) {
			System.out.println("La relación de países de Europa ordenados por superficie es: ");
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getContinente().equalsIgnoreCase(nombre) == true) {
					ordenar.add(lista.get(i));
				}
			}
			Collections.sort(ordenar);
			for (int i = 0; i < ordenar.size(); i++) {
				System.out.println(i + ".-" + ordenar.get(i).getNombre() + "           " + "Poblacion: "
						+ ordenar.get(i).getSuperficie());
			}

		} else {
			System.out.println("Ese continente no se encuentra en la lista");
		}
	}

	public void juegoCapitales() {
		int numAcierto = 0;
		int numFallo = 0;
		boolean salir = false;
		Random r1 = new Random();
		while (salir == false) {
			System.out.println("Número aciertos:" + numAcierto + "    " + "Numero Fallos: " + numFallo);

			int num = r1.nextInt(lista.size());
			System.out.println("Escribe la capita de " + lista.get(num).getNombre());
			String valor = Aplicacion.teclado.nextLine();
			Aplicacion.teclado.nextLine();
			if (valor.equalsIgnoreCase("FIN") == true) {
				salir = true;
			} else {
				if (lista.get(num).getCapital().equalsIgnoreCase(valor) == true) {
					numAcierto++;
				} else {
					numFallo++;
				}
			}
		}

	}
}
