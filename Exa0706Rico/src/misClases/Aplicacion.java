package misClases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Aplicacion {

	final static String URL = "jdbc:mysql://localhost:3306/ricoricoweb";
	final static String USUARIO = "root";
	final static String PWD = "root";
	
	static Scanner teclado=new Scanner(System.in);
	
	public static void main(String[] args) {
		// Abrimos la conexi�n con la base de datos
		// Utilizo un try-with-resources para que se cierre autom�ticamente la conexi�n
		try(Connection conexion=DriverManager.getConnection(URL, USUARIO, PWD)){

			boolean salir=false;
			while(salir==false) {
				// Muestro el men� de usuario
				System.out.println("1-Buscar recetas");
				System.out.println("2-Mostrar receta");
				System.out.println("3-Salir");
				
				int opcion=teclado.nextInt();
				teclado.nextLine();
				switch(opcion) {
				case 1:
					buscarRecetas(conexion);
					break;
				case 2:
					mostrarReceta(conexion);
					break;
				case 3:
					salir=true;
					break;
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Hubo un problema abriendo la conexi�n");		
		}
		
		// Cerramos el teclado
		teclado.close();
	}
	
	
	static void buscarRecetas(Connection conexion) {
	
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		// Pedimos el t�rmino de b�squeda
		System.out.print("Introduce el t�rmino de b�squeda :");
		String termino=Aplicacion.teclado.nextLine();

		String consulta="SELECT DISTINCT r.codReceta, r.nombre, dificultad, minutosPreparacion\r\n"
				+ "FROM recetas r INNER JOIN ingredientesporrecetas ipr ON(r.codReceta=ipr.codReceta)\r\n"
				+ "INNER JOIN ingredientes i ON (ipr.codIngrediente=i.codIngrediente)\r\n"
				+ "WHERE r.nombre LIKE ? OR i.nombre LIKE ? \r\n"
				+ "ORDER BY dificultad, r.nombre";
		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	pStatement.setString(1, "%"+termino+"%");
        	pStatement.setString(2, "%"+termino+"%");
            try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			int codigo=resultado.getInt("codReceta");
        			String nombre=resultado.getString("nombre");
        			String dificultad=resultado.getString("dificultad");
        			int minutos=resultado.getInt("minutosPreparacion");
        			// Muestro por pantalla los datos del pa�s
        			System.out.println("Cod Receta : " + codigo +
        						   	   "\tNombre : " + nombre +
        						   	   "\tDificultad : " + dificultad +
        						   	   "\tMinutos : " + minutos);
        		}
        		// Chequeo que si no hab�a datos, se muestre un mensaje que lo indique
        		if(numRegistros==0)
        			System.out.println("No hay ning�n registro con ese nombre");
        		
        	} catch (SQLException e) {
    			System.out.println("Hubo un error capturando los datos");
        	}
        } catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
		
	
	}

	static void mostrarReceta(Connection conexion) {
		
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		// Pedimos el t�rmino de b�squeda
		System.out.print("Introduce el c�digo de la receta :");
		int codigo=Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();

		String consulta="SELECT nombre, dificultad, minutosPreparacion, numComensales\r\n"
				+ "FROM recetas\r\n"
				+ "WHERE codReceta=?";
		
		StringBuilder datosReceta=new StringBuilder();
		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	pStatement.setInt(1, codigo);
            try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			String nombre=resultado.getString("nombre");
        			String dificultad=resultado.getString("dificultad");
        			int minutos=resultado.getInt("minutosPreparacion");
        			int numComensales=resultado.getInt("numComensales");
        			
        			// Muestro por pantalla los datos del pa�s
        			System.out.println("Rico Rico web");
        			datosReceta.append("Rico Rico web"+"\n");
        			System.out.println("-------------------------");
        			datosReceta.append("-------------------------"+"\n");
        			System.out.println("Nombre : " + nombre +
        						   	   "\tDificultad : " + dificultad +
        						   	   "\tMinutos : " + minutos +
        						   	   "\tNum comensales : " + numComensales);
        			datosReceta.append("Nombre : " + nombre +
						   	   "\tDificultad : " + dificultad +
						   	   "\tMinutos : " + minutos +
						   	   "\tNum comensales : " + numComensales+"\n");
        			System.out.println("-------------------------");
        			datosReceta.append("-------------------------"+"\n");
        		}
        		
        		// Chequeo que si no hab�a datos, se muestre un mensaje que lo indique
        		if(numRegistros==0)
        			System.out.println("No hay ning�n registro con ese nombre");
        		else {
        			mostrarIngredientesReceta(conexion,codigo, datosReceta);
        			mostrarPasosReceta(conexion, codigo, datosReceta);
        			
        			// Le preguntamos al usuario si quiere exportarla  a un fichero
        			System.out.print("�Quieres exportarla a un fichero S/N");
        			String respuesta=teclado.nextLine();
        			if(respuesta.equalsIgnoreCase("s")) {
        	   			System.out.print("�Introduce la ruta donde guardarlo : ");
            			String ruta=teclado.nextLine();
            			
            			try (BufferedWriter fichero=new BufferedWriter(new FileWriter(ruta))) {
            				fichero.write(datosReceta.toString());
            				
            			} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        		}
        		
        	} catch (SQLException e) {
    			System.out.println("Hubo un error capturando los datos");
        	}
        } catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
	}

	
	
	static void mostrarIngredientesReceta(Connection conexion, int codigo, StringBuilder datosReceta) {
		
		if(conexion==null) {
			return;
		}
		

		String consulta="SELECT i.nombre, ipr.medida, ipr.esOpcional\r\n"
				+ "FROM recetas r INNER JOIN ingredientesporrecetas ipr ON(r.codReceta=ipr.codReceta)\r\n"
				+ "INNER JOIN ingredientes i ON (ipr.codIngrediente=i.codIngrediente)\r\n"
				+ "WHERE r.codReceta = ?";
		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	pStatement.setInt(1, codigo);
            try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;

    			System.out.println("Ingredientes:");
    			datosReceta.append("Ingredientes:"+"\n");
    			

        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			String nombre=resultado.getString("nombre");
        			String medida=resultado.getString("medida");
        			boolean esOpcional=resultado.getBoolean("esOpcional");
        			
        			// Muestro por pantalla los datos del pa�s
        			String opcional="";
        			if(esOpcional==true)
        				opcional=" (opcional)";
        			System.out.println(nombre + " - " + medida + opcional);
        			datosReceta.append(nombre + " - " + medida + opcional +"\n");
        		}
    			System.out.println("-------------------------");
    			datosReceta.append("-------------------------" +"\n");
        		
        	} catch (SQLException e) {
    			System.out.println("Hubo un error capturando los datos");
        	}
        } catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
	}

	static void mostrarPasosReceta(Connection conexion, int codigo, StringBuilder datosReceta) {
		
		if(conexion==null) {
			return;
		}
		

		String consulta="SELECT numPaso, descripcion, esOpcional\r\n"
				+ "FROM recetas r INNER JOIN pasospreparacionrecetas ppr ON(r.codReceta=ppr.codReceta)\r\n"
				+ "WHERE r.codReceta = ? \r\n"
				+ "ORDER BY numPaso";
		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	pStatement.setInt(1, codigo);
            try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;

    			System.out.println("Pasos:");
    			datosReceta.append("Pasos:" +"\n");

        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			int numPaso=resultado.getInt("numPaso");
        			String descripcion=resultado.getString("descripcion");
        			boolean esOpcional=resultado.getBoolean("esOpcional");
        			
        			// Muestro por pantalla los datos del pa�s
        			String opcional="";
        			if(esOpcional==true)
        				opcional="(Opcional) ";
        			System.out.println(numPaso + " - " + opcional + descripcion);
        			datosReceta.append(numPaso + " - " + opcional + descripcion +"\n");
        		}
    			System.out.println("-------------------------");
    			datosReceta.append("-------------------------" +"\n");
    			
        		
        	} catch (SQLException e) {
    			System.out.println("Hubo un error capturando los datos");
        	}
        } catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
	}
	
	
}
