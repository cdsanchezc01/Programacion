package misclases;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class app {

	public static void main(String[] args) {
		
		Stack<String> pila=new Stack<String>();
		
		String fichero="c://datos//quijote.txt";
		FileReader f = null; 
		BufferedReader reader = null;
		String linea = null; 


		try {
			f = new FileReader(fichero);
			reader = new BufferedReader(f);
			
			while ((linea = reader.readLine()) != null) {
					pila.push(linea);
			}
			while(!pila.isEmpty()) {
				String lineaPila=pila.pop();
			System.out.println(lineaPila);
			}
		
		
		} catch (Exception e) {
			System.err.println("Fichero no encontrado " + fichero);
		
		}
		finally {
		     if(reader!=null)
			try {
			  reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
