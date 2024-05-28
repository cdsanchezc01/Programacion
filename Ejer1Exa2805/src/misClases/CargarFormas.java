package misClases;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CargarFormas {

	public static ArrayList<Forma> cargarFormas(String nombre) {
		ArrayList<Forma> formas = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(nombre))) {
			while (scanner.hasNextLine()) {
				
				String linea = scanner.nextLine();
				String[] partes = linea.split(" ");
				if (partes[0].equals("T")) {
					Coordenada p1 = new Coordenada(Double.parseDouble(partes[1]), Double.parseDouble(partes[2]),
							Double.parseDouble(partes[3]));
					Coordenada p2 = new Coordenada(Double.parseDouble(partes[4]), Double.parseDouble(partes[5]),
							Double.parseDouble(partes[6]));
					Coordenada p3 = new Coordenada(Double.parseDouble(partes[7]), Double.parseDouble(partes[8]),
							Double.parseDouble(partes[9]));
					Triangulo triangulo = new Triangulo(p1, p2, p3);
					formas.add(triangulo);
					
				} else if (partes[0].equals("Q")) {
					Coordenada p1 = new Coordenada(Double.parseDouble(partes[1]), Double.parseDouble(partes[2]),
							Double.parseDouble(partes[3]));
					Coordenada p2 = new Coordenada(Double.parseDouble(partes[4]), Double.parseDouble(partes[5]),
							Double.parseDouble(partes[6]));
					Coordenada p3 = new Coordenada(Double.parseDouble(partes[7]), Double.parseDouble(partes[8]),
							Double.parseDouble(partes[9]));
					Coordenada p4 = new Coordenada(Double.parseDouble(partes[10]), Double.parseDouble(partes[11]),
							Double.parseDouble(partes[12]));
					Cuadrilatero cuadrilatero = new Cuadrilatero(p1, p2, p3, p4);
					formas.add(cuadrilatero);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return formas;
	}
}
