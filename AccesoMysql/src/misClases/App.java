package misClases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) {

//		Datos dataBase
		final String URL = "jdbc:mysql://localhost:3306/world";
		final String USUARIO = "root";
		final String PWD = "9876";

		Connection conexion = null;
		Statement statement = null;
		ResultSet resultado = null;

//      paso 1 Cargamos dinamicamente el controlador del driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

//			Paso 2 abrimos una conexion con el servidor
			conexion = DriverManager.getConnection(URL, USUARIO, PWD);

//			paso 3 Lanzamos una consulta sobre el servidor 

			statement = conexion.createStatement();

			String consulta = "select code, name, continent, population from country order by population desc";

//			Lanzamos consulta
			resultado = statement.executeQuery(consulta);
//			Procesamos los registros resultantes
			while (resultado.next() == true) {
//				paso 4
//				El cursor interno esta apuntando al registro a procesar 

				String codigo = resultado.getString(1);
				String nombre = resultado.getString(2);
				String continente = resultado.getString(3);
				int poblacion = resultado.getInt(4);

				System.out.println(codigo + "-" + nombre + "-" + continente + "-" + poblacion);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Paso 5 cerramos conexion y objetos cerrados 
		finally {
			if (resultado != null)
				try {
					resultado.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
		}

	}

}
