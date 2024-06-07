package misClases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
	static Scanner teclado = new Scanner(System.in);

	
	public static void main(String[] args) {
		
			metodoEntrada();
	}
	
	
	
	
	
	public static Vehiculo metodoEntrada() {
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>();

		boolean salir = false;
		while (salir==false) {
			System.out.println("0-Salir");
			System.out.println("1-Añadir coche");
			System.out.println("2-Añadir motocicleta");
			System.out.println("3-Guardar csv");
			System.out.println("4-Mostrar informacion vehiculo");

			int opcion = teclado.nextInt();
			teclado.nextLine();
			switch (opcion) {
			case 0:
				salir = true;
				break;
			case 1:
				System.out.println("Introduce la matricula ");
				String matricula = teclado.nextLine();
				System.out.print("Introduce la marca del coche: ");
				String marcaCoche = teclado.nextLine();
				System.out.print("Introduce el modelo del coche: ");
				String modeloCoche = teclado.nextLine();
				System.out.print("Introduce la potencia del coche (en CV): ");
				int caballosCoche = teclado.nextInt();
				teclado.nextLine();

				Coche nuevoCoche = new Coche(marcaCoche, modeloCoche, caballosCoche,matricula);
				lista.add(nuevoCoche);
				break;
				
			case 2:
				System.out.println("Introduce la matricula ");
				String matricula1 = teclado.nextLine();
				System.out.print("Introduce la marca de la motocicleta: ");
				String marcaMoto = teclado.nextLine();
				System.out.print("Introduce el modelo de la motocicleta: ");
				String modeloMoto = teclado.nextLine();
				System.out.print("Introduce la cilindrada de la motocicleta (en cc): ");
				int cilindradaMoto = teclado.nextInt();
				teclado.nextLine();

				Motocicleta nuevaMoto = new Motocicleta(marcaMoto,modeloMoto, cilindradaMoto,matricula1);
				lista.add(nuevaMoto);
				break;
				
			case 3:
				System.out.println("Introduce la ruta del fichero ");
				String ruta = teclado.nextLine();
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
					for (Vehiculo dato : lista) {
						dato.guardarDatoCsv(writer, ';');
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			case 4:
				System.out.print("Introduce la matricula");
				String matricula = teclado.nextLine();

					for (int i = 0; i < lista.size(); i++) {
						if (lista.get(i).getNombre().equalsIgnoreCase(matricula) == true) {
							return lista.get(i);
						}
					}

					return null;
				
			default:
				System.out.println("Opción no válida");
				break;
			}
		}
		return null;
	}
}
