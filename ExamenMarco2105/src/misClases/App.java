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
import java.util.Scanner;

public class App {
	static Scanner teclado= new Scanner(System.in);
	public static void main(String[] args) {
		
		
		
		// TODO Auto-generated method stub

		final String URL = "jdbc:mysql://localhost:3306/bdcitascelebres";
		final String USUARIO = "root";
		final String PWD = "root";
		
		// Abrimos la conexi�n con la base de datos
		// Utilizo un try-with-resources para que se cierre autom�ticamente la conexi�n
		try(Connection conexion=DriverManager.getConnection(URL, USUARIO, PWD)){

			
			
			boolean salir=false;
			while(salir==false) {
				// Muestro el men� de usuario
				System.out.println("1-Mostrar Embalse");
				System.out.println("2-Mostrar estado Embalses");
				System.out.println("3-resumen Provincias");
				System.out.println("4-Realizar medicio");
				System.out.println("5-Guardar datos a fichero");
				System.out.println("0-Salir");
				
				int opcion=teclado.nextInt();
				teclado.nextLine();
				
				switch(opcion) {
				case 1:
					mostrarCita(conexion);
					break;
				case 2:
					buscarCitas(conexion);
					break;
				case 3:
					resumenCitas(conexion);
					break;
				case 4:
					expotarCsv(conexion);
					break;
				case 0:
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
		
	static String	establecerIdioma(Connection conexion) {
	
	
		
		System.out.println("Elige el idioma del que quieres: 1= ESPAÑOL y 2= INGLES");
		int codigo=App.teclado.nextInt();
		
		
		String idioma="";
		if(codigo==1)
			return idioma=" i.nombre like Español ;";
		else if (codigo==2)
			return idioma = " i.nombre like Ingles ;";
		
		return idioma;  
	} 
	
	
	
	
		
	static	void mostrarCita(Connection conexion){
			
		
		String consulta="select c.cita, a.nombre\r\n"
				+ "				from citas c\r\n"
				+ "				join autores a on c.codAutor = a.codAutor\r\n"
				+ "				join idiomas i on i.codIdioma = c.codIdioma\r\n"
				+ "				where i.nombre like 'Español'\r\n"
				+ "				order by rand() limit 1;";
		
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros     	
        	try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			String cita=resultado.getString("c.cita");
        			String nombre=resultado.getString("a.nombre");        			
        			
        			
        			System.out.println(cita + "("+ nombre + ")"); 
        					
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
	
	static void buscarCitas(Connection conexion) {
		
		
		
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		System.out.println("Escribe la palabra o autor por la que quieres que aparezca la cita");
		String filtro = App.teclado.nextLine();
		
		String where="";
		
			where = " a.nombre like" + "'%"+filtro+"%' or c.cita like" + "'%"+filtro+"%' ;" ;
			
		
		
		String consulta= "select c.cita, a.nombre\r\n"
				+ "from citas c\r\n"
				+ "join autores a on c.codAutor = a.codAutor\r\n"
				+ "join idiomas i on i.codIdioma = c.codIdioma\r\n"
				+ "where = "+ where;
				
		
		
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
        	try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			String cita=resultado.getString("c.cita");
        			String nombre=resultado.getString("a.nombre");  
        			
        			System.out.println(cita + "("+ nombre + ")"); 
        				
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
		
	
	static void	resumenCitas(Connection conexion) {
		
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		
		System.out.println("Elige el criterio d eordenacion siendo: A.- Alfabetico por Autor y descendentemente por citas y D. Descendentemente por citas y alfabetico por autor");
		String opcion = App.teclado.nextLine();
		
		String orderBy="";
		if(opcion.equalsIgnoreCase("A")) {
			orderBy= " ORDER BY a.nombre asc, numCitas desc;";
		}	else if(opcion.equalsIgnoreCase("D")){
			orderBy= " ORDER BY  numCitas desc, a.nombre asc;";
		}
		String consulta="select a.codAutor, a.nombre, count(c.codCita) as numCitas\r\n"
				+ "from citas c\r\n"
				+ "join autores a on c.codAutor = a.codAutor\r\n"
				+ "join idiomas i on i.codIdioma = c.codIdioma\r\n"
				+ "where i.nombre = 'Español'\r\n"
				+ "group by a.codAutor " + orderBy;
		// Creo un PreparedStatement para ejecutar la sentencia parametrizada
        try( PreparedStatement pStatement=conexion.prepareStatement(consulta)){
        	// Le asigno valor a los par�metros
                	
        	try( ResultSet resultado=pStatement.executeQuery()){
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			// Leo los datos del registro
        			int codAutor=resultado.getInt("a.codAutor");
        			String nombre=resultado.getString("a.nombre");        			
        			int numCitas=resultado.getInt("numCitas");
        			
        			System.out.println(codAutor + "-----" + nombre + "--------" + numCitas);
        						
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

	static void	exportarCsv(Connection conexion){
		
		
					System.out.print("Introduce la ruta donde quieres que se almacene el fichero : ");
					String rutaFichero=App.teclado.nextLine();

					System.out.print("Introduce el separador que quieres usar (; por defecto) : ");
					String cadenaSeparador=App.teclado.nextLine();
					String separador;
					if(cadenaSeparador.isEmpty())
						separador=";";
					else separador=cadenaSeparador;
					
					
					String consulta="select c.cita,a.nombre,i.nombre\r\n"
							+ "from citas c\r\n"
							+ "join autores a on c.codAutor = a.codAutor\r\n"
							+ "join idiomas i on i.codIdioma = c.codIdioma";
					
					// Creo un PreparedStatement para ejecutar la sentencia parametrizada y un BufferedWriter para escribir en el fichero
			        try( BufferedWriter fichero=new BufferedWriter(new FileWriter(rutaFichero));
			        	 Statement statement=conexion.createStatement(); ){
			        	
			        	try( ResultSet resultado=statement.executeQuery(consulta)){
			    			// Escribo la cabecera el fichero los datos
			        		System.out.println("Desea introducir una linea decabecera, Elija 1 para poner linea d ecabecera y 2 si no la deseas" );
			        		int opcion=App.teclado.nextInt();
			        		
			        		if(opcion==1) 
			        		
			        		fichero.write("Cita"+separador+"NombreAutor"+separador+"NombreIdioma");
			        		
			        		int numRegistros=0;
			        		while(resultado.next()) {
			        			numRegistros++;
			       
								// Leo los campos de los registros
								String cita=resultado.getString("c.cita");
								String nombreAutor=resultado.getString("a.nombre");
								String nombreIdioma=resultado.getString("i.nombre");
								

								// Escribo en el fichero los datos
			            		fichero.write(cita + separador + nombreAutor + separador + nombreIdioma );        			
			        		
			        		else
			        				

					            		fichero.write(cita + separador + nombreAutor + separador + nombreIdioma );        			
					        			
			        			   				
			        			
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
			        	        
			        
		
		
	
		
		
		
		
		
		
	
		



