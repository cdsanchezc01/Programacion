package misClases;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<FormaGeometrica> lista = new ArrayList<FormaGeometrica>();

		boolean salir = false;
		while (salir == false) {
			System.out.println("0-Salir");
			System.out.println("1-Añadir cuadrado");
			System.out.println("2-Añadir rectángulo");
			System.out.println("3-Añadir círculo");
			System.out.println("4-Añadir triángulo");
			System.out.println("5-Mostrar datos lista");

			int opcion = teclado.nextInt();
			teclado.nextLine();
			switch (opcion) {
			case 0:
				salir = true;
				break;
			case 1:
				System.out.print("Introduce el tamaño del lado del cuadrado :");
				float tamaño = teclado.nextFloat();
				teclado.nextLine();

				Cuadrado nuevoCuadrado = new Cuadrado(tamaño);
				lista.add(nuevoCuadrado);
				break;
			case 2:
				System.out.print("Introduce el tamaño de la base del rectángulo :");
				float base = teclado.nextFloat();
				teclado.nextLine();

				System.out.print("Introduce el tamaño de la altura del rectángulo :");
				float altura = teclado.nextFloat();
				teclado.nextLine();

				Rectangulo nuevoRectangulo = new Rectangulo(base, altura);
				lista.add(nuevoRectangulo);

				break;
			case 3:
				System.out.print("Introduce el tamaño del radio del círculo : ");
				float radio = teclado.nextFloat();
				teclado.nextLine();

				Circulo nuevoCirculo = new Circulo(radio);
				lista.add(nuevoCirculo);

				break;
			case 4:
				System.out.print("Introduce el lado 1 del triángulo : ");
				float lado1 = teclado.nextFloat();
				teclado.nextLine();
				System.out.print("Introduce el lado 2 del triángulo : ");
				float lado2 = teclado.nextFloat();
				teclado.nextLine();
				System.out.print("Introduce el lado 3 del triángulo : ");
				float lado3 = teclado.nextFloat();
				teclado.nextLine();

				Triangulo nuevoTriangulo = new Triangulo(lado1, lado2, lado3);
				lista.add(nuevoTriangulo);
				break;
			case 5:

				for (int i = 0; i < lista.size(); i++) {
					FormaGeometrica forma = lista.get(i);

					System.out.println(
							"La forma " + i + " tiene " + forma.getNumLados() + " lados ." + "Su perímetro es : "
									+ forma.calcularPerimetro() + "Su área es : " + forma.calcularArea());

				}
				break;
			}

		}

	}

}
