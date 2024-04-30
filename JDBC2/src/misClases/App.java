package misClases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

	static Scanner teclado = new Scanner(System.in);

	static ArrayList<Pais> lista = new ArrayList<Pais>();

	public static void main(String[] args) {

		final String URL = "jdbc:mysql://localhost:3306/world";
		final String USUARIO = "root";
		final String PWD = "9876";

		// Abrimos la conexi�n con la base de datos
		// Utilizo un try-with-resources para que se cierre autom�ticamente la conexi�n
		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PWD)) {

			// Cargo la lista de los pa�ses y sus capitales en la lista
			cargarListaCapitales(conexion);

			boolean salir = false;
			while (salir == false) {
				// Muestro el men� de usuario
				System.out.println("1-Mostrar Lista");
				System.out.println("2-");
				System.out.println("3-");
				System.out.println("4-");
				System.out.println("5-Salir");

				int opcion = teclado.nextInt();
				teclado.nextLine();
				switch (opcion) {
				case 1:
					mostrarDatosPais(conexion);
					break;
				case 2:
					(conexion);
					break;
				case 3:
					(conexion);
					break;
				case 4:
					();
					break;
				case 5:
					salir = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Hubo un problema abriendo la conexi�n");
		}

		// Cerramos el teclado
		teclado.close();
	}

	static void mostrarDatosPais(Connection conexion) {
		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}

		// Pedimos el nombre del pa�s
		System.out.print("Introduce el nombre del pais:");
		String nomPais = App.teclado.nextLine();
		
		System.out.println("Introduce el orden");
		String orden = App.teclado.nextLine();
		
		System.out.println("Introduce el continetente");
		String continente = App.teclado.nextLine(); 		
				

		String consulta = FAlta cosnutla

		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
		try (PreparedStatement pStatement = conexion.prepareStatement(consulta)) {
			// Le asigno valor a los par�metros
			pStatement.setString(1, nomPais);
			try (ResultSet resultado = pStatement.executeQuery()) {
				int numRegistros = 0;
				while (resultado.next()) {
					numRegistros++;
					// Leo los datos del registro
					String codigo = resultado.getString("code");
					String nombre = resultado.getString("name");
					String continente = resultado.getString("continent");
					int poblacion = resultado.getInt("population");
					// Muestro por pantalla los datos del pa�s
					System.out.println(codigo + " - " + nombre + " - " + continente + " - " + poblacion);
				}
				// Chequeo que si no hab�a datos, se muestre un mensaje que lo indique
				if (numRegistros == 0)
					System.out.println("No hay ning�n registro con ese nombre");

			} catch (SQLException e) {
				System.out.println("Hubo un error capturando los datos");
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
	}
}	
	