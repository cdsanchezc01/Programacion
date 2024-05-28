package misClases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		final String URL = "jdbc:mysql://localhost:3306/BDCatastro";
		final String USUARIO = "root";
		final String PWD = "9876";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PWD)) {

			boolean salir = false;
			while (salir == false) {
				// Muestro el menú de usuario
				System.out.println("1-Mostrar lista de propiedades");
				System.out.println("2-Mostrar media");
				System.out.println("3-Resumen Inmuebles");
				System.out.println("4-Exportar a texto");
				System.out.println("6-Salir");

				int opcion = teclado.nextInt();
				teclado.nextLine();
				switch (opcion) {
				case 1:
					listarPropiedades(conexion, PWD);
					break;
				case 2:
					calcularMedia(conexion);
					break;
				case 3:
					resumenInmuebles(conexion);
					break;
				case 4:
				exportarTexto(conexion);
					break;
				case 6:
					salir = true;
					break;
				}
			}

		} catch (SQLException e) {
			System.out.println("Hubo un problema abriendo la conexion");
			e.printStackTrace();
		}
	}

	public static void listarPropiedades(Connection conexion, String nombre) {
		System.out.println("Introduce tu nombre");
		nombre = App.teclado.nextLine();

		String consulta = "       SELECT  pr.NIF, pr.nombre, pr.apellidos,  p.CodPropiedad, p.Tipo, p.MetrosCuadrados, p.ValorCatastral, l.Nombre \r\n"
				+ "				FROM propiedades p\r\n"
				+ "                JOIN localidades l ON p.CodLocalidad = l.CodLocalidad \r\n"
				+ "				JOIN propietarios pr ON p.NIFPropietario = pr.NIF "
				+ " 			where pr.nombre = ?;";
		try (PreparedStatement pStatement = conexion.prepareStatement(consulta)) {
			pStatement.setString(1, nombre);
			ResultSet resultSet = pStatement.executeQuery();

			System.out.println("Lista de propiedades:");
			while (resultSet.next()) {
				String nif = resultSet.getString("Nif");
				
				String apellidos = resultSet.getString("pr.apellidos");
				int codPropiedad = resultSet.getInt("CodPropiedad");
				String tipo = resultSet.getString("Tipo");
				float metrosCuadrados = resultSet.getFloat("MetrosCuadrados");
				float valorCatastral = resultSet.getFloat("ValorCatastral");
				String localidad = resultSet.getString("l.nombre");
				

				System.out.println("Nif: " + nif + " Nombre: " +  nombre  + "Apellidos: "
						+ apellidos + " CodPropiedad: " + codPropiedad + " Tipo: " + tipo + " MetrosCuadrados: " + metrosCuadrados + " ValorCatastral: " + valorCatastral + " Localidad: " + localidad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void calcularMedia(Connection conexion) {

		System.out.println("Indica el tipo");
		String tipo = App.teclado.nextLine();
		System.out.println("Indica la localidad");
		String localidad = App.teclado.nextLine();

		String consulta = "SELECT l.Nombre, round(avg(p.MetrosCuadrados),2) AS mediaMetros, round(Avg(p.ValorCatastral),2) AS mediaValor, p.tipo\r\n"
				+ "FROM propiedades p JOIN localidades l ON p.CodLocalidad = l.CodLocalidad\r\n"
				+ "WHERE p.Tipo = ? ";
		
		if (!localidad.isEmpty()) {
			consulta += " AND l.Nombre = ? ";
		}
		consulta += " GROUP BY l.Nombre";

		try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
			statement.setString(1, tipo);
			if (!localidad.isEmpty()) {
				statement.setString(2, localidad);
			}

			ResultSet resultSet = statement.executeQuery();
			System.out.println("Tipo de propiedad: " + tipo);

			while (resultSet.next()) {
				String local = resultSet.getString("Nombre");
				double mediaMetros = resultSet.getDouble("mediaMetros");
				double mediaValor = resultSet.getDouble("mediaValor");

				System.out.println("Localidad: " + local + " Media metros cuadrados: " + mediaMetros
						+ " Media valor catastral: " + mediaValor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void resumenInmuebles(Connection conexion) {
		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}

		System.out.println("Introduce localidad: ");
		System.out.println("1-Misma localidad principal ");
		System.out.println("2-Diferente localidad");
		int or = teclado.nextInt();
		teclado.nextLine();

		String ordena = "";
		if (or == 1) {
			ordena = " order by l.nombre, l.CodLocalidad desc";
		} else
			ordena = " order by l.nombre, l.CodLocalidad desc";

		String consulta = "\r\n"
				+ "select l.Nombre, l.NumHabitantes, l.Provincia, p.Tipo \r\n"
				+ "from propiedades p\r\n"
				+ "join localidades l on p.CodPropiedad = l.CodLocalidad;" + ordena;

		try (Statement statement = conexion.createStatement()) {
			try (ResultSet result = statement.executeQuery(consulta)) {
				while (result.next()) {
					int localidad = result.getInt("Localidad");
	                int habitantes = result.getInt("Habitantes");
	                String provincia = result.getString("Provincia");
	                System.out.println(localidad + " - Localidad: " + habitantes + " - Habitantes" + provincia + " - Provincia: " );
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    static void exportarTexto(Connection conexion) {
        System.out.print("Introduce la ruta donde quieres que se almacene el fichero: ");
        String rutaFichero = teclado.nextLine();
        
        String consulta = "SELECT * FROM Propiedades";
        try (BufferedWriter fichero = new BufferedWriter(new FileWriter(rutaFichero));
             Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta)) {
            
            fichero.write("CodPropiedad, Tipo, MetrosCuadrados, ValorCatastral, NIFPropietario, CodLocalidad\n");
            while (result.next()) {
                int codPropiedad = result.getInt("CodPropiedad");
                String tipo = result.getString("Tipo");
                float metrosCuadrados = result.getFloat("MetrosCuadrados");
                float valorCatastral = result.getFloat("ValorCatastral");
                String nifPropietario = result.getString("NIFPropietario");
                int codLocalidad = result.getInt("CodLocalidad");
                
                fichero.write(codPropiedad + ", " + tipo + ", " + metrosCuadrados + ", " + valorCatastral + ", " + nifPropietario + ", " + codLocalidad + "\n");
            }
            System.out.println("Exportación a texto completada.");
            
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	

}
