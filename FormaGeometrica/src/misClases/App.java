package misClases;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	static Scanner teclado=new Scanner(System.in);
	public static void main(String[] args) {
		ArrayList<FormaGeometrica> lista=new ArrayList<FormaGeometrica>();
	
		boolean salir=false;
		while(salir==false) {
			
			System.out.println("0-Salir");
			System.out.println("1-Añadir cuadrado");
			System.out.println("2-Añadir rectángulo");
			System.out.println("3-Añadir círculo");
			System.out.println("4-Añadir triágulo");
			System.out.println("5-Mostrar datos lista");
			
			int opcion=teclado.nextInt();
			teclado.nextLine();
			switch(opcion) {
			case 0:
				salir=false;
				break;
			case 1:
				System.out.println("Introduce el tamaño del lado del caudrado: ");
				float tamaño=teclado.nextFloat();
				teclado.nextLine();
				Cuadrado nuevoCuadrado = new Cuadrado(tamaño);
				lista.add(nuevoCuadrado);
				
				break;
			case 2:
				System.out.println("Introduce el tamaño de la base del rectangulo: ");
				float tamaño=teclado.nextFloat();
				teclado.nextLine();
				
				System.out.println("Introduce el tamaño de la altura del rectangulo: ");
				float tamaño=teclado.nextFloat();
				teclado.nextLine();
				
				Rectangulo nuevoRectangulo = new Rectangulo(base, altura);
				lista.add(nuevoRectangulo);
				
				break;
			case 3:
				
				break;
			case 4:
				System.out.println("Introduce el lado de");
				
				break;
			case 5:
				
				for(int i=0; i<lista.size(); i++) {
					FormaGeometrica forma = lista.get(i);
					
					System.out.println("La forma " + i + " tiene " + forma.getNumLados() + " lados " + " su perimetro " +  
					forma.calcularPerimetro() + " su area es: " + forma.calcularArea());
					
				}
				break;
			
				
			}
			
		
		}

	}

}
