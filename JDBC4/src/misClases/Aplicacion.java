package misClases;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Aplicacion {

	static Scanner teclado=new Scanner(System.in);
	
	
	public static void main(String[] args) {

		final String URLMYSQL = "jdbc:mysql://database-1.cjswca0ikvg4.us-east-1.rds.amazonaws.com:3306/pruebaAWS";
		final String USUARIOMYSQL = "admin";
		final String PWDMYSQL = "Admin123";
		
		final String URLORACLE = "jdbc:oracle:thin:@database-2.cjswca0ikvg4.us-east-1.rds.amazonaws.com:1521:prueba";
		final String USUARIOORACLE = "admin";
		final String PWDORACLE = "Admin123";
		

		final String URLREDSHIFT = "jdbc:redshift://redshift-cluster-1.c9ttl8ogd8w4.us-east-1.redshift.amazonaws.com:5439/dev";
		final String USUARIOREDSHIFT = "admin";
		final String PWDREDSHIFT = "Admin123";
	
		
		// Abrimos la conexi�n con la base de datos
		// Utilizo un try-with-resources para que se cierre autom�ticamente la conexi�n
		try( 
			 Connection conexionMySQL=DriverManager.getConnection(URLMYSQL, USUARIOMYSQL, PWDMYSQL); 
			 Connection conexionOracle=DriverManager.getConnection(URLORACLE, USUARIOORACLE, PWDORACLE); 
			 Connection conexionRedshift=DriverManager.getConnection(URLREDSHIFT, USUARIOREDSHIFT, PWDREDSHIFT);
				){
			
			boolean salir=false;
			while(salir==false) {
				// Muestro el men� de usuario
				System.out.println("1-Realizar consulta sobre MySQL");
				System.out.println("2-Realizar consulta sobre Oracle");
				System.out.println("3-Realizar consulta sobre RedShift");				
				System.out.println("5-Salir");
				
				int opcion=teclado.nextInt();
				teclado.nextLine();
				switch(opcion) {
				case 1:
					ejecutarConsulta(1,conexionMySQL);
					break;
				case 2:
					ejecutarConsulta(2,conexionOracle);
					break;
				case 3:
					ejecutarConsulta(3,conexionRedshift);					
					break;
				case 5:
					salir=true;
					break;
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Hubo un problema abriendo las conexiones");
			e.printStackTrace();
		}
		
		// Cerramos el teclado
		teclado.close();
	}
	
/*******************************************************************
 *******************************************************************/

	static void ejecutarConsulta(int tipoServidor, Connection conexion) {
		
		
		String sentencia=" SELECT ventas.codVendedor, nombre,  "
			       +" totalVentasDiarias - AVG(totalVentasDiarias) OVER "
			       +" (PARTITION BY fecha) AS diferenciaMediaDiaria, "
			       +" totalVentasDiarias - MAX(totalVentasDiarias) OVER "
			       +"   (PARTITION BY fecha) AS distanciaMaximo, "
			       +" totalVentasDiarias - MIN(totalVentasDiarias) OVER "
			       +"   (PARTITION BY fecha) AS distanciaMinimo "
			       +" FROM ventas INNER JOIN vendedores ON (ventas.codVendedor=vendedores.codVendedor)"
			       +" WHERE fecha = ?";
		
		// Le pido al usuario la fecha
		System.out.println("Introduce la fecha en formato dd/mm/aaaa");
		String fechaStr=Aplicacion.teclado.nextLine();
		LocalDate fecha=null;
		try {
			fecha=LocalDate.parse(fechaStr,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
		} catch (DateTimeParseException e) {
			System.out.println("El formato de la fecha no es correcto");
			return;
		}
		
		
		try(PreparedStatement pStatement=conexion.prepareStatement(sentencia)) {
			// Sustituyo los par�metros de la sentencia
			pStatement.setDate(1,Date.valueOf(fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
			
			// Ejecuto la sentencia
			try(ResultSet resultado=pStatement.executeQuery()){
				int numRegistros=0;
				
				while(resultado.next()) {
					numRegistros++;
					// Obtenemos los datos de los campos del registro al que apunta el cursor
					int codVendedor=resultado.getInt("codVendedor");
					String nombreVendedor=resultado.getString("nombre");
					float diferenciaMediaDiaria=resultado.getFloat("diferenciaMediaDiaria");					
					float distanciaMaximo=resultado.getFloat("distanciaMaximo");
					float distanciaMinimo=resultado.getFloat("distanciaMinimo");	
					
					System.out.println(codVendedor+" ::: "+nombreVendedor+" ::: "+diferenciaMediaDiaria+" ::: "+distanciaMaximo+" ::: "+distanciaMinimo);
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
	
/*******************************************************************
 *******************************************************************/

	
}
