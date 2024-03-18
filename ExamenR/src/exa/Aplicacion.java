package exa;

import java.util.Scanner;

public class Aplicacion {
	
	static Scanner teclado=new Scanner(System.in);
	public static int tipoOrdenacion=0;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		GestorPaises gestor=new GestorPaises();
		
		try {
			gestor.cargarDatosLista("C:\\Users\\usuario\\Desktop\\programacion2\\Paises.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean salir=false;
		while(salir==false) {
			System.out.println("*****************************************************************");
			System.out.println("1.-Salir");
			System.out.println("2.-Información de un país");
			System.out.println("3.-Información de países en un intervalor de población");
			System.out.println("4.-Mostrar países por continentes(Ordenador por población)");
			System.out.println("5.-Mostrar países por continentes(Ordenador por superficie)");
			System.out.println("6.-Actualizar población de un país");
			System.out.println("7.-Juego capitales");
			System.out.println("*****************************************************************");
			
			
			int opcion=teclado.nextInt();
			teclado.nextLine();
			switch(opcion) {
			case 1:
				
				salir=true;
				break;
			case 2:
				gestor.mostrarInfo();
				break;
			case 3:
				gestor.mostrarPaisIntervalo();
				break;
			case 4:
				gestor.mostrarPaisesPoblacion();
				break;
			case 5:
				gestor.mostrarPaisesSuperficie();
				break;
			case 6:
				gestor.actualizarPoblación();
				break;
			case 7:
				gestor.juegoCapitales();
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
	}
	}
}
