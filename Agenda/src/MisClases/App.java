package MisClases;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Agenda miAgenda=new Agenda(100);
		Scanner teclado=new Scanner(System.in);
		
		boolean salir=false;
		while(salir==false) {
			System.out.println("1 - Mostrar entradas");
			System.out.println("2 - Añadir entrada");
			System.out.println("3 - Modificar entrada");
			System.out.println("4 - Borrar entrada");
			System.out.println("5 - Buscar Entrada");
			System.out.println("6 - Compactar agenda");
			System.out.println("9 - Salir");
			
			String opcion=teclado.nextLine();
			
			if(opcion.equals("1")) {
				miAgenda.mostrarEntradasAgenda();
			}
			if(opcion.equals("2")) {
				miAgenda.insertarEntrada();	
			}
			if(opcion.equals("3")) {
				miAgenda.insertarEntrada();
			}
			if(opcion.equals("4")) {
				miAgenda.borrarEntrada();
			}
			if(opcion.equals("5")) {
				miAgenda.buscarAgenda();
			}
			if(opcion.equals("6")) {
				miAgenda.compactarAgenda();
			}
			if(opcion.equals("9")) {
				salir=true;
			}
		}

	}

}
