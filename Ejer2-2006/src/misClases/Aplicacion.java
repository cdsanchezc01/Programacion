package misClases;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Aplicacion {

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		
		Gestor gestor=new Gestor();
		
		boolean salir=false;
		while(salir==false) {
			System.out.println("1 - Mostrar vehiculo");
			System.out.println("2 - ");
			System.out.println("3 - Salir");

			
			int opcion=teclado.nextInt();
			teclado.nextLine();
			
			switch(opcion) {
			case 1:
				gestor.mostrarVehiculo();
				break;
			case 2:

				break;

			case 3:
				salir=true;
				break;
			}
		}
	}
}
