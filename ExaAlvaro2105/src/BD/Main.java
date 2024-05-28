package BD;

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


public class Main {
	
	static Scanner teclado=new Scanner(System.in);
	static int idioma = 1;


	public static void main(String[] args) {

		final String URL = "jdbc:mysql://localhost:3306/bdcitascelebres";
		final String USUARIO = "root";
		final String PWD = "12345";
		
		
		try(Connection conexion = DriverManager.getConnection(URL, USUARIO, PWD)){
			
			boolean salir=false;
			while(salir==false) {
				// Muestro el men� de usuario
				System.out.println("0-Establecer idioma por defecto");
				System.out.println("1-Mostrar cita aleatoria");
				System.out.println("2-Buscar citas");
				System.out.println("3-Resumen citas por autor");
				System.out.println("4-Exportar base de datos a csv");
				System.out.println("5-Salir");
				
				int opcion=teclado.nextInt();
				teclado.nextLine();
				switch(opcion) {
				case 0:
					establecerIdioma(conexion);
					break;
				case 1:
					mostrarCitaAleatoria(conexion);
					break;
				case 2:
					buscarCitas(conexion);
					break;
				case 3:
					resumenCitasPorAutor(conexion);
					break;
				case 4:
					exportarCSV(conexion);
					break;
				case 5:
					salir=true;
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Hubo un problema abriendo la conexion");
		}
	}
	
	static void establecerIdioma(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		System.out.println("Introduce el codigo del idioma que quieres comprobar que existe: ");
		int codigo = teclado.nextInt();
		teclado.nextLine();
		
		if(existeCodigoIdioma(conexion,codigo)) {
			idioma=codigo;
		}
		else System.out.println("No existe ese idioma en la base de datos");
		
		
	}
	
	static boolean existeCodigoIdioma(Connection conexion,int codigo) {
		boolean existe = false;
		
		String consulta = "select * from idiomas where codIdioma = ?";
		
		try(PreparedStatement pStatement = conexion.prepareStatement(consulta)){
			pStatement.setInt(1, codigo);
			//int resultado = pStatement.executeUpdate();
			try(ResultSet result = pStatement.executeQuery()){
				int numRegistros = 0;
				while(result.next()) {
					numRegistros++;
				}
				if(numRegistros == 1) {
					existe=true;
				}
				//return existe;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return existe;
	}
	
	static void mostrarCitaAleatoria(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		String consulta = "select c.cita,concat('(',a.nombre,')') autor from citas c join autores a on c.codAutor=a.codAutor join idiomas i on c.codIdioma= i.codIdioma where i.codIdioma = " + Main.idioma + " order by rand() limit 1";
		
		try(Statement statement = conexion.createStatement()){
			try(ResultSet result = statement.executeQuery(consulta)){
				while(result.next()) {
					String cita = result.getString("cita");
					String autor = result.getString("autor");
					
					System.out.println(cita + autor);
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void buscarCitas(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		System.out.println("Introduce el termino por el cual quieres buscar citas: ");
		String termino = teclado.nextLine();
		
		String consulta = "select c.cita,concat('(',a.nombre,')') autor from citas c join autores a on c.codAutor=a.codAutor join idiomas i on c.codIdioma= i.codIdioma where (a.nombre like ? or c.cita like ?) and i.codIdioma = " + Main.idioma + "";
		
		try(PreparedStatement pStatement = conexion.prepareStatement(consulta)){
			String like="%"+termino+"%";
			pStatement.setString(1, like);
			pStatement.setString(2, like);
			try(ResultSet result = pStatement.executeQuery()){
				int numRegistros = 0;
				while(result.next()) {
					numRegistros++;
					String cita = result.getString("cita");
					String autor = result.getString("autor");
					
					System.out.println(cita + autor);
				}
				if(numRegistros == 0) {
					System.out.println("Se ha ejecutado la sentencia pero no ha encontrado nada segun el termino " + termino);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	static void resumenCitasPorAutor(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		System.out.println("Que orden quieres usar: ");
		System.out.println("1-Alfabetico por autor y descendente por numero de citas ");
		System.out.println("2-Descendente por numero de citas y alfabetico por autor");
		int or = teclado.nextInt();
		teclado.nextLine();
		
		String orden = "";
		if(or == 1){
			orden = " order by a.nombre, Numero desc";
		}
		else orden = " order by Numero desc, a.nombre";
		
		String consulta = "select a.codAutor, a.nombre, count(a.codAutor) Numero from citas c join autores a on c.codAutor=a.codAutor join idiomas i on c.codIdioma=i.codIdioma where i.codIdioma= " + Main.idioma + " group by a.codAutor" + orden;
		
		try(Statement statement = conexion.createStatement()){
			try(ResultSet result = statement.executeQuery(consulta)){
				while(result.next()) {
					int codAutor = result.getInt("codAutor");
					String nombre = result.getString("nombre");
					int numero = result.getInt("Numero");
					
					System.out.println(codAutor + " ---- " + nombre + " ----- " + numero);
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void exportarCSV(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		System.out.print("Introduce la ruta donde quieres que se almacene el fichero : ");
		String rutaFichero=teclado.nextLine();

		System.out.print("Introduce el separador que quieres usar (; por defecto) : ");
		String cadenaSeparador=teclado.nextLine();
		String separador;
		if(cadenaSeparador.isEmpty())
			separador=";";
		else separador=cadenaSeparador;
		
		System.out.println("Quieres introducir una cabecera:");
		System.out.println("1-Si");
		System.out.println("2-No");
		int cab = teclado.nextInt();
		teclado.nextLine();
		
		String consulta="select c.cita, a.nombre,i.nombre from citas c join autores a on c.codAutor=a.codAutor join idiomas i on c.codIdioma=i.codIdioma";
		
		try(BufferedWriter fichero=new BufferedWriter(new FileWriter(rutaFichero));
	        	 Statement statement=conexion.createStatement(); ){
			try(ResultSet result=statement.executeQuery(consulta)){
				if(cab == 1) {
					fichero.write("Cita" + separador + "NombreAutor" + separador + "NombreIdioma");
				}
				
				int numRegistros = 0;
				while(result.next()) {
					numRegistros++;
					String cita = result.getString("c.cita");
					String autor = result.getString("a.nombre");
					String idioma = result.getString("i.nombre");
					
					fichero.write(cita + separador + autor + separador + idioma+"\n");
				}
				if(numRegistros==0)
        			System.out.println("No hay ningun registro que escribir en el fichero");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
