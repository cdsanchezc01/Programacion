package misClases;

import java.util.Scanner;

public class App {

	static Scanner teclado= new Scanner(System.in);

	public static void main(String[] args) {
		
//		Creamos el objeto para gestionar las piezas
		GestorExamen gestor = new GestorExamen();
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
			System.out.println(" 1- Añadir examen");
			System.out.println(" 2- Buscar examen");
			System.out.println(" 3- Borrar examen");
			System.out.println(" 4- Modificar nota examen");
			System.out.println(" 0- Salir");
			
			int opcion=teclado.nextInt();
			teclado.nextLine();
			switch(opcion) {
			case 1:
				gestor.añadirExamen();
				break;
			case 2:
				gestor.econtrarExamen();
				break;
			case 3:
				gestor.borrarExamen();
				break;
			case 4: 
				gestor.modificarNotaExamen();
				
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
