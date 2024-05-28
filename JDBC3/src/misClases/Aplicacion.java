package misClases;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Aplicacion {

	static Scanner teclado=new Scanner(System.in);

	public static void main(String[] args) {

		final String URL = "jdbc:mysql://localhost:3306/infaqua";
		final String USUARIO = "usInfaqua";
		final String PWD = "12345";
		
		// Abrimos la conexi�n con la base de datos
		// Utilizo un try-with-resources para que se cierre autom�ticamente la conexi�n
		try(Connection conexion=DriverManager.getConnection(URL, USUARIO, PWD)){
			boolean salir=false;
			while(salir==false) {
				// Muestro el men� de usuario
				System.out.println("1-Mostrar informaci�n embalse");
				System.out.println("2-Mostrar estado embalses provincia");
				System.out.println("3-Mostrar resumen por provincias de comunidad");
				System.out.println("4-Actualizar nivel de embalse");
				System.out.println("5-Exportar csv");
				System.out.println("6-Exportar fichero binario");
				System.out.println("7-Salir");
				
				int opcion=teclado.nextInt();
				teclado.nextLine();
				switch(opcion) {
				case 1:
					mostrarInformacionEmbalse(conexion);
					break;
				case 2:
					mostrarEstadoProvincia(conexion);
					break;
				case 3:
					resumenProvincias(conexion);
					break;
				case 4:
					actualizarNivelEmbalse(conexion);
					break;
				case 5:
					exportarCsv(conexion);
					break;
				case 6:
					exportarFicheroBinario(conexion);
					break;
				case 7:
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
	
/*******************************************************************
 *******************************************************************/
	
	static void mostrarInformacionEmbalse(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		// Pedimos al usuario los datos para filtrar
		
		System.out.print("Elige el criterio de filtrado c[C�digo] Otra[Nombre]:");
		String criterio=Aplicacion.teclado.nextLine();
		
		String cadenaWhere="";
		int codigo=0;
		String nombreEmbalse="";
		
		if(criterio.equalsIgnoreCase("c")) {
			cadenaWhere=" WHERE codEmbalse = ? ";
			System.out.print("Introduce el c�digo del embalse : ");
			codigo=Aplicacion.teclado.nextInt();
			Aplicacion.teclado.nextLine();
		}
		else {
			cadenaWhere=" WHERE nombre = ? ";
			System.out.print("Introduce el nombre del embalse : ");
			nombreEmbalse=Aplicacion.teclado.nextLine();
		}
			
		String consulta="SELECT CodEmbalse, Nombre, Capacidad, NivelActual, VariacionSemanal"
					 + " FROM Embalses "
					 + cadenaWhere; 
		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
    		if(criterio.equalsIgnoreCase("c")) {
        		pStatement.setInt(1, codigo);
        	} else {
        		pStatement.setString(1, nombreEmbalse);
        	}
        	
        	try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			int codEmbalse=resultado.getInt("CodEmbalse");
        			String nombre=resultado.getString("Nombre");
        			int capacidad=resultado.getInt("Capacidad");
        			int nivelActual=resultado.getInt("NivelActual");
        			float variacionSemanal=resultado.getFloat("variacionSemanal");
        			
        			// Muestro por pantalla los datos del pa�s
        			System.out.println("************************");
        			System.out.println("C�digo :"+codEmbalse);
        			System.out.println("Nombre :"+nombre);
        			System.out.println("Capacidad :"+capacidad);
        			System.out.println("Nivel actual :"+nivelActual);
        			System.out.println("Variaci�n semanal :"+variacionSemanal);
        			System.out.println("************************");
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
	
/*******************************************************************
 *******************************************************************/
	
	static void mostrarEstadoProvincia(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		// Pedimos al usuario los datos para filtrar
		System.out.print("Introduce el nombre de la provincia : ");
		String provincia=Aplicacion.teclado.nextLine();

		System.out.print("Introduce el tipo de orden ([a] alfab�tico / otra capacidad) :");
		String tipoOrden=Aplicacion.teclado.nextLine();
		String cadenaOrden;
		if(tipoOrden.equalsIgnoreCase("a"))
			cadenaOrden=" ORDER BY embalses.nombre ";
		else cadenaOrden=" ORDER BY embalses.capacidad DESC ";
		

		String consulta="SELECT Embalses.CodEmbalse, Embalses.Nombre, Capacidad, NivelActual, VariacionSemanal"
					 + " FROM embalses INNER JOIN EmbalsesXProvincias ON(Embalses.codEmbalse=EmbalsesXProvincias.codEmbalse) "
					 + " INNER JOIN provincias ON(EmbalsesXProvincias.codProvincia=Provincias.codProvincia) "
					 + " WHERE provincias.nombre=? " 
					 + cadenaOrden;

		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	pStatement.setString(1, provincia);
        	try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			int codEmbalse=resultado.getInt("CodEmbalse");
        			String nombre=resultado.getString("Nombre");
        			int capacidad=resultado.getInt("Capacidad");
        			int nivelActual=resultado.getInt("NivelActual");
        			float variacionSemanal=resultado.getFloat("variacionSemanal");
        			double porcentaje=0;
        			if(capacidad>0)
        				porcentaje=100*(double)nivelActual / capacidad;
        			
        			// Muestro por pantalla los datos del pa�s
        			
        			System.out.println(codEmbalse + " - " 
        							 + nombre + " - "
        							 + capacidad + " - "
        							 + nivelActual + "(" + porcentaje + ") - "
        							 + variacionSemanal);
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
	
/*******************************************************************
 *******************************************************************/
	
	static void resumenProvincias(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		// Pedimos al usuario los datos para filtrar
		System.out.print("Introduce la comunidad  : ");
		String comunidad=Aplicacion.teclado.nextLine();

		String consulta="SELECT provincias.nombre, SUM(capacidad) as capacidadTotal, SUM(nivelActual) as nivelTotal "
					 + " FROM embalses INNER JOIN EmbalsesXProvincias ON(Embalses.codEmbalse=EmbalsesXProvincias.codEmbalse) "
					 + " INNER JOIN provincias ON(EmbalsesXProvincias.codProvincia=Provincias.codProvincia) "
					 + " WHERE provincias.comAutonoma=? " 
					 + " GROUP BY provincias.nombre " 
					 + " ORDER BY provincias.nombre " ; 

		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	pStatement.setString(1, comunidad);
        	try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			String nombre=resultado.getString("Nombre");
        			int capacidadTotal=resultado.getInt("capacidadTotal");
        			int nivelTotal=resultado.getInt("nivelTotal");
        			double porcentaje=0;
        			if(capacidadTotal>0)
        				porcentaje=100*(double)nivelTotal / capacidadTotal;
        			
        			// Muestro por pantalla los datos del pa�s
        			
        			System.out.println(nombre + " - " 
        							 + capacidadTotal + " - "
        							 + nivelTotal + "(" + porcentaje + ")" );
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
	
/*******************************************************************
 *******************************************************************/
	
	static void actualizarNivelEmbalse(Connection conexion) {
		
		// Pedimos al usuario los datos que necesitamos
		System.out.print("Introduce el c�digo del embalse  : ");
		int codEmbalse=Aplicacion.teclado.nextInt();
		Aplicacion.teclado.nextLine();
		
		
		System.out.print("Introduce el nuevo nivel  : ");
		float nuevoNivel=Aplicacion.teclado.nextFloat();
		Aplicacion.teclado.nextLine();
		
		float nivelActual=buscarNivelActualEmbalse(conexion,codEmbalse);
		
		float diferencia=0;
		if(nivelActual!=-1)
			diferencia=nuevoNivel - nivelActual;
		else {
			System.out.println("El embalse debe estar dado de alta para poder continuar");
			return;
		}
		
		LocalDate fechaActual=LocalDate.now();
		LocalTime horaActual=LocalTime.now();
		
		// Comenzamos una nueva transacci�n
		try {
			conexion.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Insertamos el nuevo registro en la tabla de mediciones
		int retorno=insertarNuevaMedicion(conexion,codEmbalse,nuevoNivel,diferencia,fechaActual,horaActual);
		if(retorno==1) {
			// El insert se ha hecho correctamente
			
			// Actualizo los datos del registro en la tabla de embalses
			retorno=actualizarRegistroEmbalse(conexion,codEmbalse,nuevoNivel,diferencia);
			if(retorno==1) {
				try {
					conexion.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("La operaci�n se realiz� correctamente");
			} else {
				try {
					conexion.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// El insert no se ha hecho 
			try {
				conexion.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
/*******************************************************************
 *******************************************************************/

	public static int insertarNuevaMedicion(Connection conexion, int codEmbalse,float nuevoNivel,float diferencia, LocalDate fechaActual,LocalTime horaActual) {
		int retorno=-1;
		
		String sentencia=" INSERT INTO mediciones(Fecha,Hora,Cantidad,Variacion,codEmbalse) " 
						+ " VALUES (?,?,?,?,?) ";
		
		try (PreparedStatement pStatement=conexion.prepareStatement(sentencia)) {
			// Sustituyo las interrogaciones
			pStatement.setDate(1,Date.valueOf(fechaActual));
			pStatement.setTime(2, Time.valueOf(horaActual)); 
			pStatement.setFloat(3, nuevoNivel); 
			pStatement.setFloat(4, diferencia); 
			pStatement.setInt(5, codEmbalse); 
			
			int numRegistrosAfectados=pStatement.executeUpdate();
			if(numRegistrosAfectados>0)
				retorno=1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	
/*******************************************************************
 *******************************************************************/

	public static int actualizarRegistroEmbalse(Connection conexion, int codEmbalse,float nuevoNivel,float diferencia) {
		int retorno=-1;
		
		String sentencia=" UPDATE Embalses "
						+ " SET NivelActual = ?, VariacionSemanal = ? "
						+ " WHERE CodEmbalse = ? ";
		
		
		try (PreparedStatement pStatement=conexion.prepareStatement(sentencia)) {
			// Sustituyo las interrogaciones
			pStatement.setFloat(1, nuevoNivel); 
			pStatement.setFloat(2, diferencia); 
			pStatement.setInt(3, codEmbalse); 
			
			int numRegistrosAfectados=pStatement.executeUpdate();
			if(numRegistrosAfectados>0)
				retorno=1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
/*******************************************************************
 *******************************************************************/
	
	static public float buscarNivelActualEmbalse(Connection conexion,int codEmbalse) {
		float res=-1;
		

		String consulta="SELECT nivelActual "
					 + " FROM embalses "
					 + " WHERE codEmbalse=? " ; 

		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	pStatement.setInt(1, codEmbalse);
        	try( ResultSet resultado=pStatement.executeQuery()){
        		while(resultado.next()) {
        			// Leo los datos del registro
        				res=resultado.getFloat("nivelActual");
       			}
        	} catch (SQLException e) {
    			System.out.println("Hubo un error capturando los datos");
        	}
        } catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		}
		
		return res;
	}
	
	
/*******************************************************************
 *******************************************************************/
	
	static void exportarCsv(Connection conexion) {
		if (conexion == null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}

		String consulta = "SELECT comAutonoma, provincias.nombre as provincia, \r\n"
				+ "	   embalses.nombre as embalse,  capacidad, nivelActual, \r\n"
				+ "       100*nivelActual/Capacidad as porcentaje\r\n" + "FROM embalses INNER JOIN embalsesxprovincias \r\n"
				+ "   ON(embalses.codEmbalse=embalsesxprovincias.codEmbalse)\r\n"
				+ " INNER JOIN provincias ON( provincias.codProvincia=embalsesxprovincias.codProvincia) \r\n"
				+ " ORDER BY comAutonoma, provincia, embalse ";

		// Creo un Statement para ejecutar la sentencia
		try (Statement statement = conexion.createStatement();
				BufferedWriter fichero = new BufferedWriter(new FileWriter("c:\\ejemplos\\embalses.csv"))) {

			try (ResultSet resultado = statement.executeQuery(consulta)) {

				// Escribimos la cabecera del fichero
				fichero.write("Comunidad;Provincia;Embalse;Capacidad;nivelActual;Porcentaje\n");

				while (resultado.next()) {
					// Leo los datos del registro
					String comunidad = resultado.getString("comAutonoma");
					String provincia = resultado.getString("provincia");
					String embalse = resultado.getString("embalse");
					float capacidad = resultado.getFloat("capacidad");
					float nivelActual = resultado.getFloat("nivelActual");
					float porcentaje = resultado.getFloat("porcentaje");

					// Guardo los datos en el fichero csv
					fichero.write(comunidad + ";" + provincia + ";" + embalse + ";" + capacidad + ";" + nivelActual + ";"
							+ porcentaje + "\n");
				}
			} catch (SQLException e) {
				System.out.println("Hubo un error capturando los datos");
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error ejecutando el SELECT");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
/*******************************************************************
 *******************************************************************/
	
static void exportarFicheroBinario(Connection conexion) {
	if (conexion == null) {
		System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
		return;
	}

	String consulta = "SELECT comAutonoma, provincias.nombre as provincia, \r\n"
			+ "	   embalses.nombre as embalse,  capacidad, nivelActual, \r\n"
			+ "       100*nivelActual/Capacidad as porcentaje\r\n" + "FROM embalses INNER JOIN embalsesxprovincias \r\n"
			+ "   ON(embalses.codEmbalse=embalsesxprovincias.codEmbalse)\r\n"
			+ " INNER JOIN provincias ON( provincias.codProvincia=embalsesxprovincias.codProvincia) \r\n"
			+ " ORDER BY comAutonoma, provincia, embalse ";

	// Creo un Statement para ejecutar la sentencia
	try (Statement statement = conexion.createStatement();
			DataOutputStream fichero = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream("c:\\ejemplos\\embalses.dat")))) {

		try (ResultSet resultado = statement.executeQuery(consulta)) {

			while (resultado.next()) {
				// Leo los datos del registro
				String comunidad = resultado.getString("comAutonoma");
				String provincia = resultado.getString("provincia");
				String embalse = resultado.getString("embalse");
				float capacidad = resultado.getFloat("capacidad");
				float nivelActual = resultado.getFloat("nivelActual");
				float porcentaje = resultado.getFloat("porcentaje");

				// Guardo los datos en el fichero binario
				fichero.writeUTF(comunidad);
				fichero.writeUTF(provincia);
				fichero.writeUTF(embalse);
				fichero.writeFloat(capacidad);
				fichero.writeFloat(nivelActual);
				fichero.writeFloat(porcentaje);
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error capturando los datos");
		}
	} catch (SQLException e) {
		System.out.println("Hubo un error ejecutando el SELECT");
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
}
