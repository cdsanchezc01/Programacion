package misClases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Gestor {

	private ArrayList<Coche> lista;
	
	
	Gestor(){
		lista=new ArrayList<Coche>();
	}
	
	
	


	void mostrarVehiculo() {
		
		System.out.print("Escribe la matr�cula del veh�culo a mostrar: ");
		String matricula=Aplicacion.teclado.nextLine();
		
		Coche vehiculo=null;
		if((vehiculo=buscarMatriculaLista(matricula))!=null) {
			// Existe el veh�culo
			vehiculo.mostrarFicha();
		}
		else System.out.println("No existe ning�n veh�culo con esa matr�cula");
	}



	void cargarDatosCSV() {

		// Borramos los datos previos que tuviera la lista
		lista.clear();
		
		System.out.print("Introduce la ruta donde est� el fichero de datos: ");
		String rutaFichero=Aplicacion.teclado.nextLine();
		
		try (BufferedReader fichero=new BufferedReader(new FileReader(rutaFichero))) {
			String linea;
			while((linea=fichero.readLine())!=null)
			{
				// Procesamos la l�nea le�da
				String campos[]=linea.split(",");
				if(campos[0].equals("N")) {
					CocheNuevo nuevoCoche=new CocheNuevo();
					nuevoCoche.cargarDatosLineaFichero(linea);
					lista.add(nuevoCoche);
				} else if(campos[0].equals("S")) {
					CocheSegunda nuevCoche=new CocheSegunda();
					nuevCoche.cargarDatosLineaFichero(linea);
					lista.add(nuevCoche);
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}

   