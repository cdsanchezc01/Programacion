package misClases;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class App {

	public static void main(String[] args) {

	}

	public static void convertirTextoBinario() throws Exception {
		BufferedReader fichero = new BufferedReader(new FileReader("c:\\Datos\\paises.csv"));

		DataOutputStream ficheroBinario = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream("c:\\Datos\\paises.bin")));

		String linea;
		int numLinea = 0;
		while ((linea = fichero.readLine()) != null) {
			numLinea++;
			if (numLinea > 1) {
//			Tenemos los datos del registro en linea

				String[] campos = linea.split(";");
				ficheroBinario.writeUTF(campos[0]); // Nombre del pais
				ficheroBinario.writeUTF(campos[1]); // Capital del pais
				ficheroBinario.writeUTF(campos[2]); // Continente del pais
				int poblacion = Integer.parseInt(campos[3]);
				ficheroBinario.writeInt(poblacion); // Poblacion del pais
				float superfice = Float.parseFloat(campos[4]);
				ficheroBinario.writeFloat(superfice); // Poblacion del pais

			}
		}
	}
			
	public static void convertirBinarioTexto() {
		
	}
		
		}
	
	

		
	


