package examen;

import java.util.Scanner;

public class Aplicacion {

	static Scanner teclado = new Scanner(System.in);
	static int tipoOrdenacion;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GestorLista gestor = new GestorLista();

		// Leemos los datos del fichero y los guardamos en la lista
		try {
			gestor.cargarDatosFichero();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean salir = false;

		while (salir == false) {
			System.out.println("1-Salir");
			System.out.println("2-Datos de un país");
			System.out.println("3-Países en un intervalo de población");
			System.out.println("4-Países de un continente por población");
			System.out.println("5-Países de un continente por superficie");
			System.out.println("6-Modificar población de país");
			System.out.println("7-Juego de las capitales");

			int opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
			case 1:
				salir = true;
				break;
			case 2:
				gestor.mostrarDatosPais();
				break;
			case 3:
				gestor.mostrarPaisesIntervalo();
				break;
			case 4:
				tipoOrdenacion = 1;
				gestor.mostrarPaisesContinente(1);
				break;
			case 5:
				tipoOrdenacion = 2;
				gestor.mostrarPaisesContinente(2);
				break;
			case 6:
				gestor.modificarPoblacionPais();
				break;
			case 7:
				gestor.juegoCapitales();
				break;
			}

		}

		teclado.close();

	}

}


