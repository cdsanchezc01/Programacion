package MisClases;

import java.util.Scanner;

public class App {
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		GestorListaPasajero gestor=new GestorListaPasajero();
		
		try {
			gestor.cargarDatosLista("C:\\Users\\usuario\\Desktop\\Ejemplo\\titanic.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		boolean salir= false;
		while(salir==false){
			System.out.println(" 1- Buscar datos pasajeros ");
			System.out.println(" 2- Calcular número de superviviente ");
			System.out.println(" 3- Mostrar estadísticas ");
			System.out.println(" 4- Guardar fichero simplificado  ");
			System.out.println(" 5- Salir ");
			
			int opcion=teclado.nextInt();
			teclado.nextLine();
			switch(opcion) {
			
			case 1:
				gestor.buscarInfoPasajero();
				break;
			case 2:
				gestor.calcularSuperviviente();
				break;
			case 3:
				gestor.calcularEstadisticas();
				break;
			case 4:
				try {
					gestor.guardarFicheroSimplificado();
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				break;
			case 5:
				salir=true;
				break;
			}
			
			
		}
		
	}

}
