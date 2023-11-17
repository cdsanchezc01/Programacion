package MisClases;

import java.util.Scanner;

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean salir=false;
		Scanner teclado=new Scanner(System.in);
		
		Juego miJuego=new Juego();
		
		while(salir==false) {
			System.out.println("Tu saldo actual es : " + miJuego.getSaldo());
			System.out.println("¿Qué quieres hacer?");
			System.out.println("1 - Recargar saldo");
			System.out.println("2 - Hacer apuesta");
			System.out.println("3 - salir");
			int opcion=teclado.nextInt();
			
			
			if(opcion==1) {
				
			
			}
			else if(opcion==2) {
				System.out.println("¿Cuánto quieres recargar?");
				int cantidad=teclado.nextInt();
				if(miJuego.hacerApuesta(cantidad)==-1)
					System.out.println("No tiene saldo suficiente");
			}
			else salir=true; 
		
		}
		//CERRAMOS EL OBJETO DE LA CLASE SCANNER
		teclado.close();
	}
}
