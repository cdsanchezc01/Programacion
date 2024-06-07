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

		final String URL = "jdbc:mysql://localhost:3306/RicoRicoWeb";
		final String USUARIO = "root";
		final String PWD = "9876";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PWD)) {

			boolean salir = false;
			while (salir == false) {
				// Muestro el menú de usuario
				System.out.println("1-Buscar receta");
				System.out.println("2-Mostrar receta");
				System.out.println("3-Exportar Receta");
				System.out.println("6-Salir");

				int opcion = teclado.nextInt();
				teclado.nextLine();
				switch (opcion) {
				case 1:
					buscarReceta(conexion);
					break;
				case 2:
					mostrarReceta(conexion);
					break;
				case 3:
				exportarReceta(conexion);
					break;
				case 4:
//				exportarTexto(conexion);
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

	static void buscarReceta(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
			return;
		}
		
		System.out.println("Dime que receta o ingrediente quires buscar");
		String campo=teclado.nextLine();
		String sentencia ="select r.CodReceta, i.nombre, r.nombre, r.dificultad, r.minutosPreparacion\r\n"
				+ "from ingredientes i\r\n"
				+ "join ingredientesporrecetas ip on i.codIngrediente = ip.CodIngrediente\r\n"
				+ "join recetas r on ip.CodReceta = r.codReceta\r\n"
				+ "where i.nombre  like ? or r.nombre like ?";
		
		try(PreparedStatement pStatement = conexion.prepareStatement(sentencia)){
			String like=campo;
			pStatement.setString(1, like);
			pStatement.setString(2, like);
			try(ResultSet resultado = pStatement.executeQuery()){
				int numRegistros = 0;
				while(resultado.next()) {
					numRegistros++;
					int codReceta=resultado.getInt("CodReceta");
        			String nombre=resultado.getString("i.nombre");
        			String dificultad=resultado.getString("dificultad");
        			int minutosPreparacion=resultado.getInt("minutosPreparacion");
        			
        			
        			System.out.println("CodReceta: " + codReceta + " nombre: " + nombre
    						+ "dificultad: " + dificultad + "minutosPreparacion: " + minutosPreparacion);
				}
				if(numRegistros == 0) {
					System.out.println("Se ha ejecutado la sentencia pero no ha encontrado nada segun el termino ");
				}
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	static void mostrarReceta(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexion no esta abierta");
			return;
		}
		
		System.out.println("Escribe el codigo de la receta");
		int campo=teclado.nextInt();
		teclado.hasNextLine();
		String sentencia ="select r.codReceta, r.nombre, r.dificultad, r.minutosPreparacion, r.numComensales, i.nombre, ip.medida, p.numPaso, p.descripcion\r\n"
				+ "from ingredientes i\r\n"
				+ "join ingredientesporrecetas ip on i.codIngrediente = ip.CodIngrediente\r\n"
				+ "join recetas r on ip.CodReceta = r.codReceta\r\n"
				+ "join pasospreparacionrecetas p on r.codReceta = p.CodReceta\r\n"
				+ "where r.CodReceta like ?;";
		
		try(PreparedStatement pStatement = conexion.prepareStatement(sentencia)){
			int like=campo;
			pStatement.setInt(1, like);
			try(ResultSet resultado = pStatement.executeQuery()){
				int numRegistros = 0;
				while(resultado.next()) {
					numRegistros++;
        			String nombre=resultado.getString("nombre");
        			String dificultad=resultado.getString("dificultad");
        			int minutosPreparacion=resultado.getInt("r.minutosPreparacion");
        			int numComensales=resultado.getInt("r.numComensales");
        			String nombreIngrediente=resultado.getString("i.nombre");
        			String medida=resultado.getString("ip.medida");
        			String numPaso=resultado.getString("p.numPaso");
        			String descripcion=resultado.getString("p.descripcion");
        			
        			System.out.println(" Rico Rico Web ");
        			System.out.println("--------------");
        			System.out.println(" Nombre Receta: " + nombre + "  dificultad: " + dificultad + "  MinutosPreparacion: " + minutosPreparacion + "  numComensales: " 
        			+ numComensales ); 
        			System.out.println("-------------------------------------------------------------------------------------------------------------------");
        			System.out.println(" Ingredientes: ");
        			System.out.println(" Nombre ingrediente : " + nombreIngrediente + " Medida Ingrediente : " + medida);
        			System.out.println("------------------------------------------------------------");
        			System.out.println(" Pasos: ");
        			System.out.println(" numPaso: " + numPaso + " descripcion: " + descripcion);
        			System.out.println(" numPaso: " + numPaso + " descripcion: " + descripcion);
        			System.out.println(" numPaso: " + numPaso + " descripcion: " + descripcion);
				}
				if(numRegistros == 0) {
					System.out.println("Se ha ejecutado la sentencia pero no ha encontrado nada segun el termino ");
				}
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	  static void exportarReceta(Connection conexion) {
	        System.out.print("Introduce la ruta donde quieres que se almacene el fichero: ");
	        String rutaFichero = teclado.nextLine();

	        System.out.print("Introduce el codReceta: ");
	        int codReceta = teclado.nextInt();
	        teclado.nextLine(); 
	        
	        String sentencia = "select r.codReceta, r.nombre, r.dificultad, r.minutosPreparacion, r.numComensales, i.nombre, ip.medida, p.numPaso, p.descripcion\r\n"
	        		+ "from ingredientes i\r\n"
	        		+ "join ingredientesporrecetas ip on i.codIngrediente = ip.CodIngrediente\r\n"
	        		+ "join recetas r on ip.CodReceta = r.codReceta\r\n"
	        		+ "join pasospreparacionrecetas p on r.codReceta = p.CodReceta\r\n"
	        		+ "where r.CodReceta like ?;";

	        try (PreparedStatement pStatement = conexion.prepareStatement(sentencia);
	             BufferedWriter fichero = new BufferedWriter(new FileWriter(rutaFichero))) {
	        	pStatement.setInt(1, codReceta);
	            try (ResultSet resultado = pStatement.executeQuery()) {
	            	int numRegistros = 0;
	            	while(resultado.next()) {
						numRegistros++;
	        			String nombre=resultado.getString("nombre");
	        			String dificultad=resultado.getString("dificultad");
	        			int minutosPreparacion=resultado.getInt("r.minutosPreparacion");
	        			int numComensales=resultado.getInt("r.numComensales");
	        			String nombreIngrediente=resultado.getString("i.nombre");
	        			String medida=resultado.getString("ip.medida");
	        			String numPaso=resultado.getString("p.numPaso");
	        			String descripcion=resultado.getString("p.descripcion");
	                    
	                    fichero.write(" Rico Rico Web " + "\n");
	                    fichero.write("--------------" + "\n");
	                    fichero.write(" Nombre Receta: " + nombre + "  dificultad: " + dificultad + "  MinutosPreparacion: " + minutosPreparacion + "  numComensales: " 
	        			+ numComensales  + "\n"); 
	                    fichero.write("-------------------------------------------------------------------------------------------------------------------"  + "\n");
	                    fichero.write(" Ingredientes: " + "\n");
	                    fichero.write(" Nombre ingrediente : " + nombreIngrediente + " Medida Ingrediente : " + medida  + "\n");
	                    fichero.write("------------------------------------------------------------"  + "\n");
	                    fichero.write(" Pasos: "  + "\n");
	                    fichero.write(" numPaso: " + numPaso + " descripcion: " + descripcion  + "\n");
	                    fichero.write(" numPaso: " + numPaso + " descripcion: " + descripcion  + "\n");
	                    fichero.write(" numPaso: " + numPaso + " descripcion: " + descripcion  + "\n");

	                } if(numRegistros == 0) {
						System.out.println("Se ha ejecutado la sentencia pero no ha encontrado nada segun el termino ");
	                }
	            }
	            System.out.println("Fichero creado correctamente en la ruta: " + rutaFichero);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}