package misClases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class App {
	static Scanner teclado=new Scanner(System.in);
	static Arrraylist<Pais> lista=new Arrraylist<Pais>();

	public static void main(String[] args) {
	
		final String URL = "jdbc:mysql://localhost:3306/world";
		final String USUARIO = "root";
		final String PWD = "9876";
		 
		Connection conexion=null;
		Statement statement=null;
		PreparedStatement pStatement=null;
		ResultSet resultado=null;
		// Paso 1. Cargamos din�micamente el controlador del driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Paso 2 Abrimos una conexi�n con el servidor 
			conexion=DriverManager.getConnection(URL, USUARIO, PWD);
			
			// Paso 3 Lanzamos una consulta sobre el servidor

			// Creamos un statement para lanzar una consulta sin par�metros
            statement = conexion.createStatement();			
            String consulta="SELECT code, name, continent, population FROM country WHERE continent=? ORDER BY population DESC";
            String sentencia2="UPDATE country SET population=47500000 where code='ESP' ";

            pStatement=conexion.prepareStatement(consulta);
            
//            Se pasa dato  
            pStatement.setString(1,"Europe");
            
            
            // Lanzamos la consulta
            resultado=pStatement.executeQuery();
            
            
            
            // Procesamos los registros resultantes
            while(resultado.next()==true) {
            	// Paso 4 Proceso los registros
            	// El cursor interno est� apuntando al registro a procesar
            	
            	String codigo=resultado.getString(1);
            	String nombre=resultado.getString(2);
            	String continente=resultado.getString(3);
            	int poblacion=resultado.getInt(4);
            	System.out.println(codigo + "-" + nombre + "-" +
            					   continente + "-" + poblacion);		
            }
            
            // Lanzamos la segunda sentencia
            int numRegistros=statement.executeUpdate(sentencia2);
            if(numRegistros!=0)
            	System.out.println("Se actualiz� el dato del registro");
            else System.out.println("No se actualiz� el dato del registro");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Paso 5 Cerramos la conexi�n y los objetos que hemos usado
			if(resultado!=null)
				try {
					resultado.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(pStatement!=null)
				try {
					pStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(conexion!=null)
				try {
					conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
		}
		
		
	}
}
