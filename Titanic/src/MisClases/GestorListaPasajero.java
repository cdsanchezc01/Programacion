package MisClases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.util.ArrayList;

public class GestorListaPasajero {

	ArrayList<Pasajero> lista;

	GestorListaPasajero() {
		lista = new ArrayList<Pasajero>();
	}

	void guardarFicheroSimplificado() throws Exception {
		System.out.println(" Introduce la ruta donde quieres guardar el fichero ");
		String ruta = App.teclado.nextLine();
		BufferedWriter nuevoFichero = new BufferedWriter(new FileWriter(ruta));

		char separador = ':';
//		Escribimos la cabezera de los registro
		nuevoFichero.write(" nombre:clase:edad:sexo:superviviente \n ");
		for (int i = 0; i < lista.size(); i++) {
			Pasajero pas = lista.get(i);

//			Componemos el formato del String que guardaremos
			String registro = pas.nombre + separador + pas.clase + separador + pas.edad + separador + pas.sexo
					+ separador + pas.superviviente + '\n';
//			Escribo nuevo fichero 
			nuevoFichero.write(registro);
		}
		nuevoFichero.close();

	}

	void cargarDatosLista(String ruta) throws Exception {

//		Comprobamos que existe el fichero
		File fichero = new File(ruta);
		if (fichero.isFile() == true) {
			BufferedReader bfr = new BufferedReader(new FileReader(ruta));
			String linea;
			int numLinea = 0;
			while ((linea = bfr.readLine()) != null) {
				numLinea++;
				if (numLinea != 1) {
					String[] camposLinea = linea.split(",");
					if (camposLinea[6] == "" || camposLinea[6] == " ")
						camposLinea[6] = "-1";

					Pasajero nuevoPasajero = new Pasajero(Boolean.parseBoolean(camposLinea[1]),
							camposLinea[4] + " " + camposLinea[3], Integer.parseInt(camposLinea[2]),
							(int) (Float.parseFloat(camposLinea[6])), camposLinea[5]);

//						AÑadimos el pasajero a la lista
					lista.add(nuevoPasajero);

				}
			}
//			Cerramos fichero
			bfr.close();

		} else
			System.out.println(" Problemas con el fichero de datos pasado ");
	}

	void buscarInfoPasajero() {
		// Le pedimos al usuario que introduzca el dato de la bsuqueda
		System.out.println(" Introduce el nombre que referencie al pasajero ");
		String terminoBusqueda = App.teclado.nextLine();
		for (int i = 0; i < lista.size(); i++) {
			Pasajero pasajero = lista.get(i);

			if (pasajero.nombre.toUpperCase().contains(terminoBusqueda.toUpperCase()) == true) {
				System.out.println(pasajero);
			}
		}
	}

	void calcularSuperviviente() {
		int numSuperviviente = 0;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).superviviente == true) {
				numSuperviviente++;
			}
			System.out.println(" El número de supervivientes fue: " + numSuperviviente);
			System.out.println(" El porcentaje de supervivientes: " + 100 * ((float) numSuperviviente / lista.size()));
		}
	}

	void calcularEstadisticas() {
		int numHombres = 0;
		int numHombresSovrevivieron = 0;
		int numMujeres = 0;
		int numMujeresSovrevivieron = 0;
		int[] numSupervivientesClase = { 0, 0, 0 };
		int[] numViajerosClase = { 0, 0, 0 };
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).sexo.equals("male")) {
				numHombres++;
				if (lista.get(i).superviviente == true)
					numHombresSovrevivieron++;
			} else {
				numMujeres++;
				if (lista.get(i).superviviente == true)
					numMujeresSovrevivieron++;
			}
			if (lista.get(i).clase > 0 && lista.get(i).clase < 4) {
				numViajerosClase[lista.get(i).clase - 1]++;
				if (lista.get(i).superviviente == true)
					numSupervivientesClase[lista.get(i).clase - 1]++;
			}
		}

//	Mostramos los datos por pantalla las estadisticas
//	Hombres
		System.out.println(" El número de hombres era: " + numHombres);
		System.out.println(" El porcentaje de los hombres supervivientes fue: "
				+ 100 * ((float) numHombresSovrevivieron / numHombres));

//	Mujeres
		System.out.println(" El número de mujeres era: " + numMujeres);
		System.out.println(" El porcentaje de los hombres supervivientes fue: "
				+ 100 * ((float) numMujeresSovrevivieron / numMujeres));

//	Datos por clase
		for (int i = 0; i < 3; i++) {
			System.out.println(" El número de viajeros en la clase " + (i + 1) + " era: " + numViajerosClase[i]);
			System.out.println(" El porcentaje de viajeros supervivientes en la clase " + (i + 1) + " fue "
					+ 100 * ((float) numSupervivientesClase[i] / numViajerosClase[i]));
		}

	}
	
	
	
	
	
	
	
	
	
	
}
