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

public class Aplicacion {

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
				System.out.println("1-Dato del pa�s");
				System.out.println("2-Insertar ciudad");
				System.out.println("3-Mostrar resumen continente");
				System.out.println("4-Juego de capitales");
				System.out.println("5-Salir");

				int opcion = teclado.nextInt();
				teclado.nextLine();
				switch (opcion) {
				case 1:
					mostrarDatosPais(conexion);
					break;
				case 2:
					insertarCiudad(conexion);
					break;
				case 3:
					resumenContinentes(conexion);
					break;
				case 4:
					juegoCapitales();
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
		System.out.print("Introduce el nombre del pa�s :");
		String nomPais = Aplicacion.teclado.nextLine();

		String consulta = "SELECT code, name, continent, population" + " FROM country" + " WHERE name=?";

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

	static void insertarCiudad(Connection conexion) {
		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}

		String sentencia = "INSERT INTO city(name,countryCode,district,population) VALUES (?,?,?,?)";

		// Pedimos al usuario los datos que necesitamos
		System.out.print("Introduce el nombre de la ciudad :");
		String ciudad = Aplicacion.teclado.nextLine();
		System.out.print("Introduce el c�digo del pa�s :");
		String codPais = Aplicacion.teclado.nextLine();

		// Comprobamos si ya existe una ciudad con ese nombre
		boolean existe = existeCiudad(conexion, ciudad, codPais);

		if (existe) {
			System.out.println("Ya existe una ciudad con ese nombre en ese pa�s");
		} else {
			System.out.print("Introduce el distrito :");
			String distrito = Aplicacion.teclado.nextLine();
			System.out.print("Introduce su poblaci�n :");
			int poblacion = Aplicacion.teclado.nextInt();
			Aplicacion.teclado.nextLine();

			try (PreparedStatement pStatement = conexion.prepareStatement(sentencia)) {
				// Sustituimos los par�metros de la sentencia por sus valores
				pStatement.setString(1, ciudad);
				pStatement.setString(2, codPais);
				pStatement.setString(3, distrito);
				pStatement.setInt(4, poblacion);

				// Ejecutamos la sentencia
				int numRegistrosAfectados = pStatement.executeUpdate();
				if (numRegistrosAfectados == 0) {
					System.out.println("No se ha insertado un registro, pero la sentencia se ejecut� correctamente");
				} else {
					System.out.println("La ciudad se a�adi� correctamente");
				}
			} catch (SQLException e) {
				// System.out.println("Se ha producido un error creando el PreparedStatement o
				// en el Insert");
				e.printStackTrace();
			}
		}
	}

	static boolean existeCiudad(Connection conexion, String ciudad, String codigoPais) {
		if (conexion == null) {
			return false;
		}

		String sentenciaSQL = "SELECT COUNT(*) FROM city WHERE name=? AND countryCode=?";

		try (PreparedStatement pStatement = conexion.prepareStatement(sentenciaSQL)) {
			// Sustituimos los par�metros de la sentencia por sus valores
			pStatement.setString(1, ciudad);
			pStatement.setString(2, codigoPais);

			// Ejecutamos la sentencia
			try (ResultSet resultado = pStatement.executeQuery()) {

				// Como s� que tiene un registro el resultado
				resultado.next();

				// Obtengo el valor del campo del registro
				int numCiudades = resultado.getInt(1);
				if (numCiudades == 0)
					return false;
				else
					return true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	static void resumenContinentes(Connection conexion) {
		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}

		String sentenciaSQL = "SELECT continent AS continente, SUM(population) as poblacion, SUM(surfaceArea) AS superficie, COUNT(*) AS numPaises\r\n"
				+ "FROM country\r\n" + "GROUP BY continent";

		try (Statement statement = conexion.createStatement()) {

			// Ejecuto la sentencia
			try (ResultSet resultado = statement.executeQuery(sentenciaSQL)) {
				// Proceso el resultado
				while (resultado.next() == true) {
					// Leo los campos de los registros
					String continente = resultado.getString("continente");
					long poblacion = resultado.getLong("poblacion");
					long superficie = resultado.getLong("superficie");
					int numPaises = resultado.getInt("numPaises");

					// Muestro por pantalla los datos
					System.out.println(continente + "-" + "Poblaci�n = " + poblacion + " - " + "Superficie = "
							+ superficie + " - " + "N�mero de pa�ses : " + numPaises);
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

	static void cargarListaCapitales(Connection conexion) {

		String sentenciaSQL = "SELECT continent AS continente, country.name AS pais, city.name AS capital \r\n"
				+ "FROM country INNER JOIN city ON(country.capital=city.ID)";

		try (Statement statement = conexion.createStatement()) {

			// Ejecuto la sentencia
			try (ResultSet resultado = statement.executeQuery(sentenciaSQL)) {
				// Proceso el resultado
				while (resultado.next() == true) {
					// Leo los campos de los registros
					String continente = resultado.getString("continente");
					String pais = resultado.getString("pais");
					String capital = resultado.getString("capital");

					// Creo un objeto con los datos del pa�s y lo a�ado a la lista
					Pais nuevoPais = new Pais(pais, capital, continente);
					lista.add(nuevoPais);
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

	static void juegoCapitales() {

		Random numAleatorio = new Random();
		int numAciertos = 0;
		int numFallos = 0;

		if (lista.isEmpty()) {
			System.out.println("No hay datos cargados en la lista");
			return;
		}

		boolean salir = false;
		System.out.println("Escribe salir cuando quieras irte");
		while (salir == false) {
			// Seleccionamos aleatoriamente un objeto de la lista

			Pais datosPais = lista.get(numAleatorio.nextInt(lista.size()));
			if (datosPais != null) {
				System.out.print("Capital de " + datosPais.getNombre() + " : ");
				String respuesta = Aplicacion.teclado.nextLine();
				if (respuesta.equalsIgnoreCase("salir"))
					salir = true;
				else {
					if (respuesta.equalsIgnoreCase(datosPais.getCapital())) {
						System.out.println("OK");
						numAciertos++;
					} else {
						numFallos++;
						System.out.println("La respuesta correcta era : " + datosPais.getCapital());
					}
					System.out.println("Aciertos : " + numAciertos + " .Fallos : " + numFallos);
				}

			}
		}

	}

}
