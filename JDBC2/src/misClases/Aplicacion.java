package misClases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

	static Scanner teclado=new Scanner(System.in);

	static ArrayList<Pais> lista=new ArrayList<Pais>();
	
	
	public static void main(String[] args) {

		final String URL = "jdbc:mysql://localhost:3306/world";
		final String USUARIO = "root";
		final String PWD = "root";
		
		// Abrimos la conexi�n con la base de datos
		// Utilizo un try-with-resources para que se cierre autom�ticamente la conexi�n
		try(Connection conexion=DriverManager.getConnection(URL, USUARIO, PWD)){

			
			// Cargo la lista de los pa�ses y sus capitales en la lista
			cargarListaCiudades(conexion);
			
			boolean salir=false;
			while(salir==false) {
				// Muestro el men� de usuario
				System.out.println("1-Mostrar lista ordenada de pa�ses seg�n poblaci�n");
				System.out.println("2-Mostrar ciudades intervalo");
				System.out.println("3-Juego de ciudades");
				System.out.println("4-Crear fichero");
				System.out.println("5-Salir");
				
				int opcion=teclado.nextInt();
				teclado.nextLine();
				switch(opcion) {
				case 1:
					mostrarListaPaises(conexion);
					break;
				case 2:
					mostrarCiudadesIntervalo(conexion);
					break;
				case 3:
					juegoCiudades();
					break;
				case 4:
					generarFichero(conexion);
					break;
				case 5:
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
	
	static void mostrarListaPaises(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		// Pedimos al usuario los datos para filtrar
		System.out.print("Introduce el nombre del continente (vac�o si quieres que sea global):");
		String continente=Aplicacion.teclado.nextLine();
		String cadenaWhere;
		if(continente.isEmpty())
			cadenaWhere="";
		else cadenaWhere=" WHERE continent=? ";
			

		System.out.print("Introduce el n�mero de pa�ses que quieres :");
		int numPaises=Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();
		
		System.out.print("Introduce el tipo de orden (a ascendente / otra descendente) :");
		String tipoOrden=Aplicacion.teclado.nextLine();
		String cadenaOrden;
		if(tipoOrden.equalsIgnoreCase("a"))
			cadenaOrden=" ORDER BY population ASC ";
		else cadenaOrden=" ORDER BY population DESC ";
			
		String consulta="SELECT name, population"
					 + " FROM country "
					 + cadenaWhere 
					 + cadenaOrden
					 + "LIMIT ?";
		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	if(cadenaWhere.isEmpty()) {
        		pStatement.setInt(1, numPaises);
        	} else {
        		pStatement.setString(1, continente);
        		pStatement.setInt(2, numPaises);
        	}
        	
        	try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			String nombre=resultado.getString("name");
        			int poblacion=resultado.getInt("population");
        			// Muestro por pantalla los datos del pa�s
        			System.out.println(numRegistros+" - "+nombre+" - "+poblacion);
        		}
        		// Chequeo que si no hab�a datos, se muestre un mensaje que lo indique
        		if(numRegistros==0)
        			System.out.println("No hay ning�n registro que mostrar para esos datos");
        		
        	} catch (SQLException e) {
    			System.out.println("Hubo un error capturando los datos");
        	}
        } catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
	}

	
	
	static void mostrarCiudadesIntervalo(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		// Pedimos al usuario los datos para filtrar
		System.out.print("Introduce el nombre del pa�s :");
		String pais=Aplicacion.teclado.nextLine();

		System.out.print("Introduce el rango inferior de habitantes :");
		int rangoInferior=Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();
		
		System.out.print("Introduce el rango inferior de habitantes :");
		int rangoSuperior=Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();
		
		System.out.print("Introduce el tipo de orden (a alfab�tico / otra poblaci�n) :");
		String tipoOrden=Aplicacion.teclado.nextLine();
		String cadenaOrden;
		if(tipoOrden.equalsIgnoreCase("a"))
			cadenaOrden=" ORDER BY city.name ";
		else cadenaOrden=" ORDER BY city.population ";
		

		String consulta="SELECT city.name, district, city.population"
					 + " FROM city INNER JOIN country ON(city.countryCode=country.code) "
					 + " WHERE country.name=? AND city.population BETWEEN ? AND ? " 
					 + cadenaOrden;

		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	pStatement.setString(1, pais);
        	pStatement.setInt(2, rangoInferior);        	
        	pStatement.setInt(3, rangoSuperior);        	
        	try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			String nombre=resultado.getString("name");
        			String distrito=resultado.getString("district");        			
        			int poblacion=resultado.getInt("population");
        			// Muestro por pantalla los datos del pa�s
        			System.out.println(nombre+" (" +distrito + ") -" + poblacion);
        		}
        		// Chequeo que si no hab�a datos, se muestre un mensaje que lo indique
        		if(numRegistros==0)
        			System.out.println("No hay ning�n registro que mostrar para esos datos");
        		
        	} catch (SQLException e) {
    			System.out.println("Hubo un error capturando los datos");
        	}
        } catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
	}
	
	
	
	static void cargarListaCiudades(Connection conexion) {

		String sentenciaSQL="SELECT city.name AS ciudad, country.name AS pais \r\n"
				+ "FROM country INNER JOIN city ON(country.code=city.CountryCode) \r\n"
				+ "WHERE city.population > 1000000";

		try (Statement statement=conexion.createStatement()) {
			
			// Ejecuto la sentencia
			try(ResultSet resultado=statement.executeQuery(sentenciaSQL)){
				// Proceso el resultado
				while(resultado.next()==true) {
					// Leo los campos de los registros
					String ciudad=resultado.getString("ciudad");
					String pais=resultado.getString("pais");

					// Creo un objeto con los datos del pa�s y lo a�ado a la lista
					Pais nuevoPais=new Pais(ciudad,pais);
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
	
	static void juegoCiudades() {
		
		Random numAleatorio=new Random();
		int numAciertos=0;
		int numFallos=0;
		
		if(lista.isEmpty()) {
			System.out.println("No hay datos cargados en la lista");
			return;
		}
		
		boolean salir=false;
		System.out.println("Escribe FIN cuando quieras irte");
		while(salir==false) {
			// Seleccionamos aleatoriamente un objeto de la lista
			
			Pais datosPais=lista.get(numAleatorio.nextInt(lista.size()));
			if(datosPais!=null) {
				System.out.print("Pa�s de " + datosPais.getCiudad() + " : ");
				String respuesta=Aplicacion.teclado.nextLine();
				if(respuesta.equalsIgnoreCase("FIN"))
					salir=true;
				else {
					if(respuesta.equalsIgnoreCase(datosPais.getPais())) {
						System.out.println("OK");
						numAciertos++;
					}
					else {
						numFallos++;
						System.out.println("La respuesta correcta era : " + datosPais.getPais());
					}
					System.out.println("Aciertos : " + numAciertos + " .Fallos : " + numFallos);
				}
			}
		}
	}


	
	static void generarFichero(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		// Pedimos al usuario los datos que necesitamos
		System.out.print("Introduce la ruta donde quieres que se almacene el fichero : ");
		String rutaFichero=Aplicacion.teclado.nextLine();

		System.out.print("Introduce el separador que quieres usar (; por defecto) : ");
		String cadenaSeparador=Aplicacion.teclado.nextLine();
		String separador;
		if(cadenaSeparador.isEmpty())
			separador=";";
		else separador=cadenaSeparador;
		

		String consulta="SELECT country.name AS pais, city.name AS capital, continent AS continente, country.population AS poblacion"
					 + " FROM city INNER JOIN country ON(city.ID=country.capital) "
					 + " ORDER BY continent, country.name "; 

		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada y un BufferedWriter para escribir en el fichero
        try( BufferedWriter fichero=new BufferedWriter(new FileWriter(rutaFichero));
        	 Statement statement=conexion.createStatement(); ){
        	
        	try( ResultSet resultado=statement.executeQuery(consulta)){
    			// Escribo la cabecera el fichero los datos
        		fichero.write("Pa�s"+separador+"Capital"+separador+"Continente"+separador+"Poblaci�n"+"\n");
        		
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			String pais=resultado.getString("pais");
        			String capital=resultado.getString("capital");        			
        			String continente=resultado.getString("continente");        			
        			int poblacion=resultado.getInt("poblacion");
        			// Escribo en el fichero los datos
            		fichero.write(pais+separador+capital+separador+continente+separador+poblacion+"\n");        			
        		}
        		// Chequeo que si no hab�a datos, se muestre un mensaje que lo indique
        		if(numRegistros==0)
        			System.out.println("No hay ning�n registro que escribir para esos datos");
        		
        	} catch (SQLException e) {
    			System.out.println("Hubo un error capturando los datos");
        	}
        } catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Hubo un error creando el fichero");
		}
	}
}
