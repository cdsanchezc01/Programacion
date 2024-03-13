import java.util.Scanner;

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);

		GestorTanques gestor = new GestorTanques();
		boolean salir = false;
		while (salir == false) {
			System.out.println("1-Mostrar estado tanque");
			System.out.println("2-Mostrar estado tanques");
			System.out.println("3-Rellenar tanque");
			System.out.println("4-Extraer tanque");
			System.out.println("5-Mostrar resumen");
			System.out.println("6-Salir");

			int opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
			case 1:
				gestor.mostrarEstadoTanque();
				break;
			case 2:
				gestor.mostrarEstadoTanques();
				break;
			case 3:
				gestor.rellenarTanque();
				break;
			case 4:
				gestor.extraerTanque();
				break;
			case 5:
				gestor.resumen();
				break;
			case 6:
				salir = true;
				break;
			}

		}

	}

}

