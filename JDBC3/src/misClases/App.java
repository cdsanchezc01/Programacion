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

	static ArrayList<Embalse> lista = new ArrayList<Embalse>();

	public static void main(String[] args) {

		final String URL = "jdbc:mysql://localhost:3306/infaqua";
		final String USUARIO = "usInfaqua";
		final String PWD = "12345";

		// Abrimos la conexi�n con la base de datos
		// Utilizo un try-with-resources para que se cierre autom�ticamente la conexi�n
		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PWD)) {

			// Cargo la lista de los pa�ses y sus capitales en la lista
			cargarListaCapitales(conexion);

			boolean salir = false;
			while (salir == false) {
				// Muestro el men� de usuario
				System.out.println("1-Datos Embalse");
				System.out.println("2-Insertar embalse");
				System.out.println("3-Mostrar resumen");
				System.out.println("4-Salir");

				int opcion = teclado.nextInt();
				teclado.nextLine();
				switch (opcion) {
				case 1:
					mostrarDatosEmbalse(conexion);
					break;
				case 2:
//					insertarEmbalse(conexion);
					break;
				case 3:
//					resumenEmbalse(conexion);
					break;

				case 4:
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
//-----------------------------------------------------------------------------
	static void mostrarDatosEmbalse(Connection conexion) {
		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
			return;
		}

		// Pedimos el nombre del ambalse
		System.out.print("Introduce el nombre del embalse :");
		String nomEmbalse = App.teclado.nextLine();

		String consulta =  "select CodEmbalse, Nombre, AnioCreacion, Capacidad, NivelActual from embalses where nombre =  ? ";
//		importante tener en cuenta si es con parametro o no. Habria diferencia con el PreparedStatement  
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
		try (PreparedStatement pStatement = conexion.prepareStatement(consulta)) {

			// Le asigno valor a los par�metros
			pStatement.setString(1, nomEmbalse);
		
			
			try (ResultSet resultado = pStatement.executeQuery()) {
				int numRegistros = 0;
				while (resultado.next()) {
					numRegistros++;
					// Leo los datos del registro
					int codEmbalse = resultado.getInt("CodEmbalse");
					String nombre = resultado.getString("Nombre");
					int anio = resultado.getInt("AnioCreacion");
					float capacidad = resultado.getFloat("Capacidad");
					float nivelActual = resultado.getFloat("NivelActual");
					// Muestro por pantalla los datos del embalse
					System.out.println(codEmbalse + " - " + nombre);
				}
				// Chequeo que si no hab�a datos, se muestre un mensaje que lo indique
				if (numRegistros == 0)
					System.out.println("No hay ningun registro con ese nombre");

			} catch (SQLException e) {
				System.out.println("Hubo un error capturando los datos");
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
	}
//---------------------------------------------------------------------------------
/*	static void cargarListaCapitales(Connection conexion) {

		String sentenciaSQL  = "select * from embalses where nombre = 'embalses'= ?;	"; 

		try (Statement statement = conexion.createStatement()) {

			// Ejecuto la sentencia
			try (ResultSet resultado = statement.executeQuery(sentenciaSQL)) {
				// Proceso el resultado
				while (resultado.next() == true) {
					// Leo los campos de los registros
					int codEmbalse = resultado.getInt("Codigo Embalse");
					String nombre = resultado.getString("Nombre");

					// Creo un objeto con los datos del embalse y lo a�ado a la lista
					Embalse nuevoEmbalse = new Embalse(codEmbalse, nombre);
					lista.add(nuevoEmbalse);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
*/
}