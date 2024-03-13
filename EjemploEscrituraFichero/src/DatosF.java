import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatosF {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Throwable {
		Scanner entrada=new Scanner(System.in);
		System.out.println(" Introduce la ruta del fichero ");
		String ruta=entrada.nextLine();
		
		BufferedWriter fichero;
		fichero=new BufferedWriter(new FileWriter(ruta));
		
		boolean salir=false;
		while(salir==false) {
			System.out.println(" Escribe una frase: ");
			String frase=entrada.nextLine();
			 if(frase.equalsIgnoreCase("salir")==true)
				 salir=true;
			 else fichero.write(frase + "\n");
		}
//		Cierro fichero
		fichero.close();
		
	static void leerFrases() {	
		BufferedReader fichero2;
		fichero2 = new BufferedReader(new FileReader(ruta));
		
		String linea;
		while(){
			
		}
	}
	}

}
