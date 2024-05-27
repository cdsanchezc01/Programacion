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

	static Scanner teclado=new Scanner(System.in);
	static int codIdioma=1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String URL = "jdbc:mysql://localhost:3306/bdcitascelebres";
		final String USUARIO = "root";
		final String PWD = "1234";
		
		try(Connection conexion=DriverManager.getConnection(URL, USUARIO, PWD)){
			
			boolean salir=false;
			while(salir==false) {
				// Muestro el men� de usuario
				System.out.println("1-Establecer idioma");
				System.out.println("2-Cita aleatoria");
				System.out.println("3-Buscar citas");
				System.out.println("4-Resumen citas");
				System.out.println("5-Exportar");
				System.out.println("6-Salir");
				
				int opcion=teclado.nextInt();
				teclado.nextLine();
				switch(opcion) {
				case 1:
					establecerIdioma(conexion);
					break;
				case 2:
					citaAleatoria(conexion);
					break;
				case 3:
					buscarIdioma(conexion);
					break;
				case 4:
					resumenAutor(conexion);
					break;
				case 5:
					exportarFichero(conexion);
					break;
				case 6:
					salir=true;
					break;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void exportarFichero(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		System.out.print("Introduce la ruta donde quieres que se almacene el fichero : ");
		String rutaFichero=teclado.nextLine();
		teclado.nextLine();
		
		System.out.print("Introduce el separador que quieres usar (; por defecto) : ");
		String cadenaSeparador=teclado.nextLine();
		String separador;
		if(cadenaSeparador.isEmpty())
			separador=";";
		else separador=cadenaSeparador;
		
		String sentencia="select c.cita,a.nombre,i.nombre as idioma from citas as c join autores as a on c.codAutor=a.codAutor join idiomas as i on i.codIdioma=c.codIdioma";
		
        try( BufferedWriter fichero=new BufferedWriter(new FileWriter(rutaFichero));
           	 Statement statement=conexion.createStatement(); ){
        	
        	try( ResultSet resultado=statement.executeQuery(sentencia)){
        		System.out.print("Quieres cabecera 1-si 2-no");
        		int opc=teclado.nextInt();
        		teclado.nextLine();
        		if(opc==1) {
        			fichero.write("Cita"+separador+"NombreAutor"+separador+"NombreIdioma"+"\n");
        		}
        		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			
        			String cita=resultado.getString("cita");
        			String nombre=resultado.getString("nombre");        			
        			String idioma=resultado.getString("idioma");        			
        		
        		
            		fichero.write(cita+separador+nombre+separador+idioma+"\n");        			
        		}
        		if(numRegistros==0)
        			System.out.println("No hay ningun registro que escribir para esos datos");
        		else System.out.println("Se ha escrito correctamente");
        		
        	}
        	
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	static void resumenAutor(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		System.out.println("Dime el modo de ordenacion 1 o 2");
		int opcion=teclado.nextInt();
		teclado.nextLine();
		
		String ordenacion="";
		if(opcion==1) {
			ordenacion="order by a.nombre,numero desc";
		}
		else if(opcion==2){
			ordenacion="order by numero desc,a.nombre";
		}
		else {
			System.out.println("NO me vale ese modo");
		}
		
		String sentencia="select a.codAutor,count(c.cita)'numero',a.nombre from citas as c join autores as a on"
				+ " c.codAutor=a.codAutor where codIdioma=1 group by a.codAutor "+ ordenacion ;
		
		try (Statement statement=conexion.createStatement()) {
			try( ResultSet resultado=statement.executeQuery(sentencia)){
	      		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			int cod=resultado.getInt("codAutor");
        			int numero=resultado.getInt("numero");
        			String nombre=resultado.getString("nombre");
        			
        			System.out.println(cod+"---"+nombre+"----"+numero);
        		}
				if(numRegistros==0) {
					System.out.println("no se ha ejecutado");
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	static void buscarIdioma(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		
		System.out.println("Dime que quieres buscar");
		String campo=teclado.nextLine();
		teclado.nextLine();
		
		String sentencia="select c.cita, a.nombre from citas as c join autores as a on c.codAutor=a.codAutor where c.cita like '%"+campo+"%' or a.nombre like '%"+campo+"%' and codIdioma="+codIdioma;
		
		try (Statement statement=conexion.createStatement()) {

			try( ResultSet resultado=statement.executeQuery(sentencia)){
	      		int numRegistros=0;
        		while(resultado.next()) {
        			numRegistros++;
        			String cita=resultado.getString("cita");
        			String nombre=resultado.getString("nombre");
        			
        			System.out.println(cita+" ("+nombre+")");
        		}
				if(numRegistros==0) {
					System.out.println("no existe nada con ese termino");
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	static void citaAleatoria(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		String sentencia="select c.cita,a.nombre from citas as c join autores as a on c.codAutor=a.codAutor where codIdioma="+codIdioma+" order by rand() limit 1";
		int numRegistros=0;
		try (Statement statement=conexion.createStatement()) {
			try(ResultSet resultado=statement.executeQuery(sentencia)){
				
				while(resultado.next()) {
					numRegistros++;
					String cita=resultado.getString("cita");
					String nombre=resultado.getString("nombre");
					
					System.out.println(cita+" ("+nombre+")");
				}
				if(numRegistros==0) {
					System.out.println("fallo");
				}

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	static void establecerIdioma(Connection conexion) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return;
		}
		System.out.println("Dime el codigo del idioma");
		int codigo=teclado.nextInt();
		teclado.nextLine();
		if(buscarCodigo(conexion,codigo)==true) {
			codIdioma=codigo;
			System.out.println("Se ha cambiado el idioma");
		}
		else {
			System.out.println("el codigo de idioma no existe");
		}
		
		
	}
	
	static boolean buscarCodigo(Connection conexion,int codigo) {
		if(conexion==null) {
			System.out.println("No puedo mostrar nada porque la conexi�n no est� abierta");
			return false;
		}
		boolean existe=false;
		String sentencia="select codIdioma from idiomas";
		
		try (Statement statement=conexion.createStatement()) {
			
			try(ResultSet resultado=statement.executeQuery(sentencia)){
				while(resultado.next()==true) {
					int cod=resultado.getInt(codIdioma);
					
					if(codigo==cod) {
						existe=true;
						break;
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("mal");
		}
		return existe;
	}
	
}
