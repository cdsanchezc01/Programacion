package examen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GestorLista {

	ArrayList<Pais> lista;

	GestorLista() {
		lista = new ArrayList<Pais>();
	}

	void escribirDatosFichero() throws Exception {
		BufferedWriter fichero = new BufferedWriter(new FileWriter("c:\\Datos\\Paises.csv"));

		// Escribimos la línea de cabecera
		fichero.write("Nombre;Capital;Continente;Población;Superficie\n");

		// Recorremos el arrayList y escribimos una línea por registro
		for (int i = 0; i < lista.size(); i++) {
			Pais p = lista.get(i);
			fichero.write(p.getNombre() + ";" + p.getCapital() + ";" + p.getContinente() + ";" + p.getPoblacion() + ";"
					+ p.getSuperficie() + "\n");
		}

		// Cierro el fichero
		fichero.close();
	}

	void cargarDatosFichero() throws Exception {

		BufferedReader fichero = new BufferedReader(new FileReader("c:\\Datos\\Paises.csv"));

		String linea;

		int numLineas = 0;

		while ((linea = fichero.readLine()) != null) {
			// Hemos leído una línea del fichero
			if (numLineas > 0) {
				String[] campos = linea.split(";");

				String nombre = campos[0];
				String capital = campos[1];
				String continente = campos[2];
				int poblacion = Integer.parseInt(campos[3]);
				float superficie = Float.parseFloat(campos[4]);
				// Creamos el objeto para ese país
				Pais nuevoPais = new Pais(nombre, continente, capital, poblacion, superficie);

				lista.add(nuevoPais);
			}

			numLineas++;
		}

		fichero.close();

	}

	Pais buscarPais(String nombrePaisBuscado) {

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNombre().equalsIgnoreCase(nombrePaisBuscado) == true) {
				return lista.get(i);
			}
		}

		return null;
	}

	void mostrarDatosPais() {
		// Pedimos al usuario el país que quiere

		System.out.print("Introduce el nombre del país : ");
		String nombre = Aplicacion.teclado.nextLine();

		Pais paisBuscado = buscarPais(nombre);
		if (paisBuscado != null) {
			System.out.println("***************************************");
			System.out.println(paisBuscado.getNombre() + "(" + paisBuscado.getCapital() + ")\t\t\t"
					+ paisBuscado.getContinente() + "\nPoblación : " + paisBuscado.getPoblacion() + "\t\t\tSuperficie :"
					+ paisBuscado.getSuperficie());

			System.out.println("***************************************");
		} else
			System.out.println("El país buscado no existe en la lista");

	}

	void mostrarPaisesIntervalo() {
		System.out.print("Introduce el valor mínimo : ");
		int valorMinimo = Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();
		System.out.print("Introduce el valor máximo : ");
		int valorMaximo = Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();

		for (int i = 0; i < lista.size(); i++) {
			int poblacion = lista.get(i).getPoblacion();
			if (poblacion >= valorMinimo && poblacion <= valorMaximo) {
				System.out.println(lista.get(i).getNombre() + "(" + lista.get(i).getContinente() + ")\t\t\tPoblación : "
						+ lista.get(i).getPoblacion());
			}
		}
	}

	void mostrarPaisesContinente(int tipo) {
		System.out.print("Introduce el nombre del continente : ");
		String continente = Aplicacion.teclado.nextLine();

		// Ordeno la lista por el criterio que marque la variable tipoOrdenacion
		Collections.sort(lista);

		int contador = 1;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getContinente().equalsIgnoreCase(continente) == true) {
				if (tipo == 1) {
					// Por población
					System.out.println(contador + lista.get(i).getNombre() + "(" + lista.get(i).getCapital()
							+ ")\tPoblación : " + lista.get(i).getPoblacion());
				} else if (tipo == 2) {
					// Por superficie
					System.out.println(contador + lista.get(i).getNombre() + "(" + lista.get(i).getCapital()
							+ ")\tSuperficie : " + lista.get(i).getSuperficie());

				}

				contador++;
			}
		}

	}

	void juegoCapitales() {
		int numAciertos = 0;
		int numFallos = 0;

		Random aleatorio = new Random();

		boolean salir = false;
		while (salir == false) {
			// Elegimos un país de forma aleatoria
			Pais p = lista.get(aleatorio.nextInt(lista.size()));

			System.out.println(" Número de aciertos : " + numAciertos + " Número de fallos : " + numFallos);
			System.out.print(" Escribe la capital de " + p.getNombre() + " : ");

			String respuesta = Aplicacion.teclado.nextLine();
			if (respuesta.equalsIgnoreCase("Fin"))
				salir = true;
			else {
				if (respuesta.equalsIgnoreCase(p.getCapital()) == true) {
					numAciertos++;
				} else
					numFallos++;
			}
		}
	}

	void modificarPoblacionPais() {

		System.out.print("Introduce el nombre del país : ");
		String pais = Aplicacion.teclado.nextLine();

		Pais datosPais = buscarPais(pais);
		if (datosPais != null) {
			// El país existe
			System.out.print("Introduce la nueva población. Vacío si lo quieres dejar como está " + "("
					+ datosPais.getPoblacion() + ") : ");
			String nuevaPoblacion = Aplicacion.teclado.nextLine();
			if (!nuevaPoblacion.isEmpty()) {
				// Actualizamos los datos del objeto en memoria
				datosPais.setPoblacion(Integer.parseInt(nuevaPoblacion));

				// Rescribimos los datos en el fichero
				try {
					escribirDatosFichero();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else
			System.out.println("No existe ningún país con ese nombre");

	}

}
