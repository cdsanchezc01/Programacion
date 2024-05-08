package misClases;

import java.util.Scanner;

public class App {
	
	static Scanner teclado= new Scanner(System.in);

	public static void main(String[] args) {
		
//		Creamos el objeto para gestionar las piezas
		GestorPieza gestor = new GestorPieza();
//		Cargamos los datos del fichero y lo añadimos al arraylist
		try {
			gestor.cargarDatosFichero();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
//		Mostramos un menu al usuario
		boolean salir=false;
		while(salir==false) {
			System.out.println(" 1- Añadir nueva pieza");
			System.out.println(" 2- Buscar pieza");
			System.out.println(" 3- Borrar pieza");
			System.out.println(" 0- Salir");
			
			int opcion=teclado.nextInt();
			teclado.nextLine();
			switch(opcion) {
			case 1:
				gestor.añadirPieza();
				break;
			case 2:
				gestor.econtrarPieza();
				break;
			case 3:
				gestor.borrarPieza();
				break;
			case 0:
				try {
					gestor.guardarDatosFichero();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				salir=true;
				break;
			}
	
		}
		if(teclado!=null)
			teclado.close();
		
		

	}

}
