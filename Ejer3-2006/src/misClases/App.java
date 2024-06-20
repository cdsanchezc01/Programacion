package misClases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

	final static String URL = "jdbc:mysql://localhost:3306/bdatp";
	final static String USUARIO = "root";
	final static String PWD = "9876";

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		// Abrimos la conexi�n con la base de datos
		// Utilizo un try-with-resources para que se cierre autom�ticamente la conexi�n
		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PWD)) {

			boolean salir = false;
			while (salir == false) {
				// Muestro el men� de usuario
				System.out.println("1-Buscar jugadores");
				System.out.println("2-Mostrar finales por jugador");
				System.out.println("3-Mostrar finales por superficie y año");
				System.out.println("4-Mostrar resumen jugador en un año");
				System.out.println("5-Salir");

				int opcion = teclado.nextInt();
				teclado.nextLine();
				switch (opcion) {
				case 1:
					buscarJugadores(conexion);
					break;
				case 2:
					mostrarFinalesJugadores(conexion);
					break;
				case 3:
					mostrarFinalesSuperficieAño(conexion);
					break;
		
				case 4:
					resumenJugadorAño(conexion);
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

	static void buscarJugadores(Connection conexion) {

		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
			return;
		}

		// Pedimos el t�rmino de b�squeda
		System.out.print("Introduce el termino de busqueda :");
		String termino = App.teclado.nextLine();

		String consulta = "select idJugador, nombre, pais, altura, mano\r\n" + "from jugadores\r\n"
				+ "where nombre LIKE ? OR pais LIKE ? \r\n" + "order by nombre";

		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
		try (PreparedStatement pStatement = conexion.prepareStatement(consulta)) {
			// Le asigno valor a los par�metros
			pStatement.setString(1, "%" + termino + "%");
			pStatement.setString(2, "%" + termino + "%");
			try (ResultSet resultado = pStatement.executeQuery()) {
				int numRegistros = 0;
				while (resultado.next()) {
					numRegistros++;
					// Leo los datos del registro
					int codigo = resultado.getInt("idJugador");
					String nombre = resultado.getString("nombre");
					String pais = resultado.getString("pais");
					int altura = resultado.getInt("altura");
					String mano = resultado.getString("mano");

					System.out.println("Codigo\t 	Nombre\t 	Pais\t 	Altura\t 	Mano\t	Golpeo");
					System.out.println(
							"-------------------------------------------------------------------------------------------");
					System.out.println("Cod:" + codigo + " Nombre:" + nombre + " Pais:" + pais + " Altura:" + altura
							+ " Mano:" + mano);

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

	static void mostrarFinalesJugadores(Connection conexion) {

		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
			return;
		}

		// Pedimos el t�rmino de b�squeda
		System.out.print("Introduce el termino de busqueda :");
		String termino = App.teclado.nextLine();

		String consulta = "select t.nombre, t.nivel, t.superficie, t.fechaComienzo, jGa.nombre, jGa.pais, jPe.nombre, jPe.pais, p.resultado\r\n"
				+ "from torneos t \r\n"
				+ "join partidos p on t.idTorneo = p.idTorneo\r\n"
				+ "join jugadores jGa on p.idGanador = jGa.idJugador\r\n"
				+ "join jugadores jPe on p.idPerdedor = jPe.idJugador\r\n"
				+ "where jGa.idJugador like ? or jGa.nombre like ?\r\n"
				+ "order by t.fechaComienzo asc;";

		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
		try (PreparedStatement pStatement = conexion.prepareStatement(consulta)) {
			// Le asigno valor a los par�metros
			pStatement.setString(1, "%" + termino + "%");
			pStatement.setString(2, "%" + termino + "%");
			try (ResultSet resultado = pStatement.executeQuery()) {
				int numRegistros = 0;
				while (resultado.next()) {
					numRegistros++;
					// Leo los datos del registro
					String torneo = resultado.getString("t.nombre");
					String nivel = resultado.getString("t.nivel");
					String superficie = resultado.getString("t.superficie");
					String fecha = resultado.getString("t.fechaComienzo");
					String ganador = resultado.getString("jGa.nombre");
					String paisG = resultado.getString("jGa.pais");
					String rival = resultado.getString("jPe.nombre");
					String paisP = resultado.getString("jPe.pais");
					String resutlado = resultado.getString("p.resultado");

					System.out.println("Torneo\t 	Superficie\t 	Fecha\t 	Ganador\t 		Rival\t 			Resultado");
					System.out.println(
							"-------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Torneo:" + torneo + "("+nivel+")" + " Superficie:" + superficie + " Fecha:" + 
							fecha + " Ganador:" + ganador + "("+paisG+")" + 
							" Rival:" + rival + "("+paisP+")"
							+ " Resultado:" + resutlado);

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
	static void mostrarFinalesSuperficieAño(Connection conexion) {

		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
			return;
		}

		// Pedimos el t�rmino de b�squeda
		System.out.print("Introduce el tipo de superficie :");
		String termino = App.teclado.nextLine();

		String consulta = "select t.nombre, t.nivel, t.superficie, t.fechaComienzo, jGa.nombre, jGa.pais, jPe.nombre, jPe.pais, p.resultado, p.ronda\r\n"
				+ "from torneos t \r\n"
				+ "join partidos p on t.idTorneo = p.idTorneo\r\n"
				+ "join jugadores jGa on p.idGanador = jGa.idJugador\r\n"
				+ "join jugadores jPe on p.idPerdedor = jPe.idJugador\r\n"
				+ "where p.ronda like 'F' and t.superficie like ? or t.fechaComienzo like ? \r\n"
				+ "order by t.fechaComienzo asc";

		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
		try (PreparedStatement pStatement = conexion.prepareStatement(consulta)) {
			// Le asigno valor a los par�metros
			pStatement.setString(1, "%" + termino + "%");
			pStatement.setString(2, "%" + termino + "%");
			try (ResultSet resultado = pStatement.executeQuery()) {
				int numRegistros = 0;
				while (resultado.next()) {
					numRegistros++;
					// Leo los datos del registro
					String torneo = resultado.getString("t.nombre");
					String nivel = resultado.getString("t.nivel");
					String superficie = resultado.getString("t.superficie");
					String fecha = resultado.getString("t.fechaComienzo");
					String ganador = resultado.getString("jGa.nombre");
					String paisG = resultado.getString("jGa.pais");
					String rival = resultado.getString("jPe.nombre");
					String paisP = resultado.getString("jPe.pais");
					String resutlado = resultado.getString("p.resultado");

					System.out.println("Torneo\t 	Superficie\t 	Fecha\t 	Ganador\t 		Rival\t 			Resultado");
					System.out.println(
							"-------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Torneo:" + torneo + "("+nivel+")" + " Superficie:" + superficie + " Fecha:" + 
							fecha + " Ganador:" + ganador + "("+paisG+")" + 
							" Rival:" + rival + "("+paisP+")"
							+ " Resultado:" + resutlado);

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
	static void resumenJugadorAño(Connection conexion) {

		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
			return;
		}

		// Pedimos el t�rmino de b�squeda
		System.out.print("Introduce el codigo del jugador y el año :");
		String termino = App.teclado.nextLine();

		String consulta = "select jGa.idJugador, jGa.nombre, jGa.pais, t.fechaComienzo,jxtg.ranking, \r\n"
				+ "jPe.idJugador, jPe.nombre, jPe.pais, t.fechaComienzo, jxtp.ranking\r\n"
				+ "from torneos t \r\n"
				+ "join partidos p on t.idTorneo = p.idTorneo\r\n"
				+ "join jugadores jGa on p.idGanador = jGa.idJugador\r\n"
				+ "join jugadores jPe on p.idPerdedor = jPe.idJugador\r\n"
				+ "join jugadoresxtorneos jxtg on (p.idGanador=jxtg.idJugador and jxtg.idTorneo=t.idTorneo)\r\n"
				+ "join jugadoresxtorneos jxtp on (p.idPerdedor=jxtp.idJugador and jxtp.idTorneo=t.idTorneo)\r\n"
				+ "where jGa.idJugador like ? and t.fechaComienzo like ?";

		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
		try (PreparedStatement pStatement = conexion.prepareStatement(consulta)) {
			// Le asigno valor a los par�metros
			pStatement.setString(1, "%" + termino + "%");
			pStatement.setString(2, "%" + termino + "%");
			try (ResultSet resultado = pStatement.executeQuery()) {
				int numRegistros = 0;
				while (resultado.next()) {
					numRegistros++;
					// Leo los datos del registro
					int codigo = resultado.getInt("jGa.idJugador");
					String nombre = resultado.getString("jGa.nombre");
					String paisG = resultado.getString("jGa.pais");
					String fechaG = resultado.getString("t.fechaComienzo");
					int rankingG = resultado.getInt("jxtg.ranking");
					
					int codigoG = resultado.getInt("jPe.idJugador");
					String nombreG = resultado.getString("jPe.nombre");
					String pais = resultado.getString("jPe.pais");
					String fecha = resultado.getString("t.fechaComienzo");
					int ranking = resultado.getInt("jxtp.ranking");
				
				

					System.out.println("Codigo\t 	Nombre\t 	Año\t"); 	
					System.out.println(
							"-------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Codigo:" + codigo + " Nombre:" + nombre + "("+paisG+")" + " Codigo:" + fecha);

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
