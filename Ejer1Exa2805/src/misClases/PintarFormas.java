package misClases;

import java.util.ArrayList;

public class PintarFormas {

		    public static void pintarFormas(ArrayList<Forma> formas) {
		        for (Forma forma : formas) {
		            forma.pintar();
		        }
		    }

		    public static void main(String[] args) {
		        ArrayList<Forma> formas = CargarFormas.cargarFormas("imagen.dat");
		        pintarFormas(formas);
		    }
		}

	

