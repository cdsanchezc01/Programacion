package classes;

import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	public static int tipoOrdenacion=0;

	public static void main(String[] args) {

		GestionDatos gestion;
		// Creo el objeto que gestionará la lista de productos
		gestion = new GestionDatos();

		// Leer contenido del fichero al iniciar la aplicación
		gestion.leerFichero();

		boolean salir = false;
		do {
			System.out.println("1- Añadir producto");
			System.out.println("2- Mostrar producto");
			System.out.println("3- Modificar precio producto");
			System.out.println("4- Modificar nombre, descripción o categoría de producto");
			System.out.println("5- Borrar producto");
			System.out.println("6- Mostrar productos con encarecimiento superior");
			System.out.println("7- Listar productos en intervalo de precios (Ordenados por precio descendente y nombre)");
			System.out.println("8- Listar productos en intervalo de precios (Ordenados por categoría y nombre)");
			System.out.println("9- Salir");

			int opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				gestion.añadirProducto();
				break;
			case 2:
				gestion.mostrarDatosProducto();
				break;
			case 3:
				gestion.modificarPrecioProducto();
				break;
			case 4:
				gestion.modificarRestoDatos();
				break;
			case 5:
				gestion.borrarProducto();
				break;
			case 6:
				gestion.mostrarProductosEncarecimientoSuperior();
				break;
			case 7:
				tipoOrdenacion=1;
				gestion.listarProductosIntervalo();
				break;
			case 8:
				tipoOrdenacion=2;
				gestion.listarProductosIntervalo();
				break;
			case 9:
				salir = true;
				break;
			}
		} while (salir == false);

		// Escribimos los datos en el fichero al salir de la aplicación
		gestion.guardarFichero();
	}
}
