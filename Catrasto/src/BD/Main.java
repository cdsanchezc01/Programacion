package BD;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        final String URL = "jdbc:mysql://localhost:3306/BDCatastro";
        final String USUARIO = "root";
        final String PWD = "12345";
        
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PWD)) {
            
            boolean salir = false;
            while (!salir) {
                // Muestro el menú de usuario
                System.out.println("1-Mostrar propietarios");
                System.out.println("2-Mostrar propiedades");
                System.out.println("3-Mostrar localidades");
                System.out.println("4-Exportar base de datos a texto");
                System.out.println("5-Exportar base de datos a binario");
                System.out.println("6-Salir");
                
                int opcion = teclado.nextInt();
                teclado.nextLine();
                switch(opcion) {
                case 1:
                    mostrarPropietarios(conexion);
                    break;
                case 2:
                    mostrarPropiedades(conexion);
                    break;
                case 3:
                    mostrarLocalidades(conexion);
                    break;
                case 4:
                    exportarTexto(conexion);
                    break;
                case 5:
                    exportarBinario(conexion);
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
    
    static void mostrarPropietarios(Connection conexion) {
        String consulta = "SELECT * FROM Propietarios";
        try (Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta)) {
            while (result.next()) {
                String nif = result.getString("NIF");
                String nombre = result.getString("Nombre");
                String apellidos = result.getString("Apellidos");
                int telefono = result.getInt("Telefono");
                Date fechaNacimiento = result.getDate("FechaNacimiento");
                System.out.println(nif + " - " + nombre + " " + apellidos + " - Tel: " + telefono + " - Nacimiento: " + fechaNacimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    static void mostrarPropiedades(Connection conexion) {
        String consulta = "SELECT * FROM Propiedades";
        try (Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta)) {
            while (result.next()) {
                int codPropiedad = result.getInt("CodPropiedad");
                String tipo = result.getString("Tipo");
                float metrosCuadrados = result.getFloat("MetrosCuadrados");
                float valorCatastral = result.getFloat("ValorCatastral");
                String nifPropietario = result.getString("NIFPropietario");
                int codLocalidad = result.getInt("CodLocalidad");
                System.out.println(codPropiedad + " - " + tipo + " - Metros: " + metrosCuadrados + " - Valor: " + valorCatastral + " - Propietario: " + nifPropietario + " - Localidad: " + codLocalidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    static void mostrarLocalidades(Connection conexion) {
        String consulta = "SELECT * FROM Localidades";
        try (Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta)) {
            while (result.next()) {
                int codLocalidad = result.getInt("CodLocalidad");
                String nombre = result.getString("Nombre");
                int numHabitantes = result.getInt("NumHabitantes");
                String provincia = result.getString("Provincia");
                System.out.println(codLocalidad + " - " + nombre + " - Habitantes: " + numHabitantes + " - Provincia: " + provincia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    static void exportarTexto(Connection conexion) {
        System.out.print("Introduce la ruta donde quieres que se almacene el fichero: ");
        String rutaFichero = teclado.nextLine();
        
        String consulta = "SELECT * FROM Propiedades";
        try (BufferedWriter fichero = new BufferedWriter(new FileWriter(rutaFichero));
             Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta)) {
            
            fichero.write("CodPropiedad, Tipo, MetrosCuadrados, ValorCatastral, NIFPropietario, CodLocalidad\n");
            while (result.next()) {
                int codPropiedad = result.getInt("CodPropiedad");
                String tipo = result.getString("Tipo");
                float metrosCuadrados = result.getFloat("MetrosCuadrados");
                float valorCatastral = result.getFloat("ValorCatastral");
                String nifPropietario = result.getString("NIFPropietario");
                int codLocalidad = result.getInt("CodLocalidad");
                
                fichero.write(codPropiedad + ", " + tipo + ", " + metrosCuadrados + ", " + valorCatastral + ", " + nifPropietario + ", " + codLocalidad + "\n");
            }
            System.out.println("Exportación a texto completada.");
            
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    static void exportarBinario(Connection conexion) {
        System.out.print("Introduce la ruta donde quieres que se almacene el fichero: ");
        String rutaFichero = teclado.nextLine();
        
        String consulta = "SELECT * FROM Propiedades";
        try (ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream(rutaFichero));
             Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta)) {
            
            while (result.next()) {
                int codPropiedad = result.getInt("CodPropiedad");
                String tipo = result.getString("Tipo");
                float metrosCuadrados = result.getFloat("MetrosCuadrados");
                float valorCatastral = result.getFloat("ValorCatastral");
                String nifPropietario = result.getString("NIFPropietario");
                int codLocalidad = result.getInt("CodLocalidad");
                
                Propiedad propiedad = new Propiedad(codPropiedad, tipo, metrosCuadrados, valorCatastral, nifPropietario, codLocalidad);
                fichero.writeObject(propiedad);
            }
            System.out.println("Exportación a binario completada.");
            
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    static class Propiedad implements Serializable {
        private static final long serialVersionUID = 1L;
        int codPropiedad;
        String tipo;
        float metrosCuadrados;
        float valorCatastral;
        String nifPropietario;
        int codLocalidad;

        public Propiedad(int codPropiedad, String tipo, float metrosCuadrados, float valorCatastral, String nifPropietario, int codLocalidad) {
            this.codPropiedad = codPropiedad;
            this.tipo = tipo;
            this.metrosCuadrados = metrosCuadrados;
            this.valorCatastral = valorCatastral;
            this.nifPropietario = nifPropietario;
            this.codLocalidad = codLocalidad;
        }
    }
}
