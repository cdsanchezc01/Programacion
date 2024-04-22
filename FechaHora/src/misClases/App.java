package misClases;

import java.text.Format;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {
	private static final DateTimeFormatter DateTimeFormatter = null;
	static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		
		boolean salir=false;
		while(salir==false) {
			System.out.println("0-Salir");
			System.out.println("1-Saludar");
			System.out.println("2-Indica la fecha en la naciste ");
			
			int opcion=teclado.nextInt();
			teclado.nextLine();
			switch(opcion) {
			
			case 0:
				salir=true;
				break;
			case 1:
				saludar();
				break;
			case 2:
				mostrarDiaNacimiento();
			}
		}
		teclado.close();
	}
		static void saludar() {
			LocalTime horaActual=LocalTime.now();
			LocalDate fechaActual=LocalDate.now();
			
			String mensaje;
			int parteHora=horaActual.getHour();
			if (parteHora >=6 && parteHora < 13)
				mensaje="Hola buenos dias ";
			else if (parteHora >=13 && parteHora <20)
				mensaje="Hola buena tardes ";
			else mensaje="Hola buena noches ";
			
			System.out.println(mensaje);
			System.out.println(" Hoy es : " + fechaActual.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
			System.err.println(" La hora actual es : " + horaActual.format(DateTimeFormatter.ofPattern("HH.mm")));
		}
	
		static void mostrarDiaNacimiento() {
			System.out.println(" Indica la fecha de tu nacimiento ");
			String texto=teclado.nextLine();
			int tipo;
			
			try {
				LocalDate fechaNacimiento;
				if(texto.contains("-"))
					fechaNacimiento = LocalDate.parse(texto);
				else fechaNacimiento = LocalDate.parse(texto,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				
				System.out.println(" El dia que naciste fue " + fechaNacimiento.getDayOfWeek());
				System.out.println(" El dia que naciste fue " + fechaNacimiento.format(DateTimeFormatter));
				
			} catch (DateTimeParseException e) {
				System.out.println(" El formato de la fecha no es el correcto ");
			
			}
			 
		}







}





