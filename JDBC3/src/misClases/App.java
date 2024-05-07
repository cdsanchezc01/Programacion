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
						System.out.println("3-Mostrar resumen continente");
						System.out.println("4-Salir");

						int opcion = teclado.nextInt();
						teclado.nextLine();
						switch (opcion) {
						case 1:
							mostrarDatosPais(conexion);
							break;
						case 2:
							insertarEmbalse(conexion);
							break;
						case 3:
							resumenEmbalse(conexion);
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

			static void mostrarDatosPais(Connection conexion) {
				if (conexion == null) {
					System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
					return;
				}

				// Pedimos el nombre del ambalse
				System.out.print("Introduce el nombre del embalse :");
				String nomEmbalse = App.teclado.nextLine();

				String consulta = "select codEmbalse, nombre, capacidad, nivelActual, variacionSemanal from embalses where = ?;	";

				// Creo un PreparedStatement para ejecutar la sentencia parametrizada
				try (PreparedStatement pStatement = conexion.prepareStatement(consulta)) {
					// Le asigno valor a los par�metros
					pStatement.setString(1, nomEmbalse);
					try (ResultSet resultado = pStatement.executeQuery()) {
						int numRegistros = 0;
						while (resultado.next()) {
							numRegistros++;
							// Leo los datos del registro
							String codEmbalse = resultado.getString("Codigo");
							String nombre = resultado.getString("nombre");
							String continente = resultado.getString("continent");
							int poblacion = resultado.getInt("population");
							// Muestro por pantalla los datos del embalse
							System.out.println(codEmbalse + " - " + nombre + " - " + continente + " - " + poblacion);
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

			static void insertarEmbalse(Connection conexion) {
				if (conexion == null) {
					System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
					return;
				}

				String sentencia = "INSERT INTO city(name,countryCode,district,population) VALUES (?,?,?,?)";

				// Pedimos al usuario los datos que necesitamos
				System.out.print("Introduce el nombre del emabalse :");
				String embalse = App.teclado.nextLine();
				System.out.print("Introduce el codigo del embalse :");
				int codEmbalse = App.teclado.nextInt();

				// Comprobamos si ya existe una ciudad con ese nombre
				boolean existe = existeEmbalse(conexion, embalse, codEmbalse);

				if (existe) {
					System.out.println("Ya existe un emabalse con ese nombre");
				} else {
					System.out.print("Introduce el distrito :");
					String distrito = App.teclado.nextLine();
					System.out.print("Introduce su poblaci�n :");
					int poblacion = App.teclado.nextInt();
				    App.teclado.nextLine();

					try (PreparedStatement pStatement = conexion.prepareStatement(sentencia)) {
						// Sustituimos los par�metros de la sentencia por sus valores
						pStatement.setString(1, embalse);
						pStatement.setInt(2, codEmbalse);

						// Ejecutamos la sentencia
						int numRegistrosAfectados = pStatement.executeUpdate();
						if (numRegistrosAfectados == 0) {
							System.out.println("No se ha insertado un registro, pero la sentencia se ejecut� correctamente");
						} else {
							System.out.println("El embalse se ha añadido correctamente");
						}
					} catch (SQLException e) {
						// System.out.println("Se ha producido un error creando el PreparedStatement o
						// en el Insert");
						e.printStackTrace();
					}
				}
			}

			static boolean existeCiudad(Connection conexion, int codEmbalse,  String nom) {
				if (conexion == null) {
					return false;
				}

				String sentenciaSQL = "SELECT COUNT(*) FROM city WHERE name=? AND countryCode=?";

				try (PreparedStatement pStatement = conexion.prepareStatement(sentenciaSQL)) {
					// Sustituimos los par�metros de la sentencia por sus valores
					pStatement.setString(1, nom);
					pStatement.setInt(2, codEmbalse);

					// Ejecutamos la sentencia
					try (ResultSet resultado = pStatement.executeQuery()) {

						// Como s� que tiene un registro el resultado
						resultado.next();

						// Obtengo el valor del campo del registro
						int numEmbalse = resultado.getInt(1);
						if (numEmbalse == 0)
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

			static void resumenEmbalse(Connection conexion) {
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

			static void cargarListaEmbalses(Connection conexion) {

				String sentenciaSQL = "SELECT continent AS continente, country.name AS pais, city.name AS capital \r\n"
						+ "FROM country INNER JOIN city ON(country.capital=city.ID)";

				try (Statement statement = conexion.createStatement()) {

					// Ejecuto la sentencia
					try (ResultSet resultado = statement.executeQuery(sentenciaSQL)) {
						// Proceso el resultado
						while (resultado.next() == true) {
							// Leo los campos de los registros
							String continente = resultado.getString("continen");
							String pais = resultado.getString("pais");
							String capital = resultado.getString("capital");

							// Creo un objeto con los datos del embalse y lo añado a la lista
							Embalse nuevoEmbalse = new Embalse(codEmbalse, nom);
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

	

		}