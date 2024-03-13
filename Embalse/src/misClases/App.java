package misClases;

import java.util.Scanner;

public class App {
	 static Scanner entrada =new Scanner(System.in);


	public static void main(String[] args) {
		GestorEmbalse gestor=new GestorEmbalse();
		boolean salir=false;
		while(salir==false) {
			System.out.println(" 1- Añadir Embalse ");
			System.out.println(" 2- Borrar Embalse ");
			System.out.println(" 3- Mostrar estado Embalse");
			System.out.println(" 4 - Mostrar estado embalses por provincia ");
			System.out.println(" 5 - Mostrar estado embalses por confederación hidrográfica ");
			System.out.println(" 6 – Modificar nivel embalse ");
			System.out.println(" 0- Salir ");
			int opcion=entrada.nextInt();
			entrada.nextLine();
			switch(opcion) {
			case 1:
				gestor.AñadirEmbalse();
				break;
			case 2:
				gestor.BorrarEmbalse();
				break;
			case 3:
				break;
			case 0:
				salir=true;
				break;
			
			}
			
		}
		

	}

}
