package MisClases;

	public class Tanque {

		final int SP95=1;
		final int SP98=2;	
		final int DSNO=3;
		final int DSEX=4;
		
		
		// Atributo
		int tipoCombustible;
		int capacidad;
		int cantidadActual;
		int contadorEntrada;
		int contadorSalida;
		
		Tanque() {
			contadorEntrada=0;
			contadorSalida=0;
			cantidadActual=0;
		}
		
		int inicializarTanque(int tipo,int capac) {
			
			if(cantidadActual>0)
				return -1;
			if(tipo!=SP95 && tipo!=SP98 && tipo!=DSNO && tipo!=DSEX)
				return -1;
			
			capacidad=capac;
			tipoCombustible=tipo;
			return 1;
		}
		
		String mostrarInfoTanque() {
			String resultado="Tipo combustible: " + tipoCombustible + "Cantidad: " + cantidadActual +  "Capacidad: " + capacidad + "C.Entrada: " + contadorEntrada +  "C. Salida: " + contadorSalida;
			
			return resultado;
		}

		String mostrarInfoTanqueV2() {
			String resultado="";
			if(capacidad>0)
				resultado="Tipo combustible: " + tipoCombustible + "Cantidad: " + cantidadActual +  "Capacidad: " + capacidad + "Porcentaje ocupación:" + 100*cantidadActual/capacidad;
			else resultado="Tipo combustible: " + tipoCombustible + "Cantidad: " + cantidadActual +  "Capacidad: " + capacidad + "Porcentaje ocupación: -";
			
			return resultado;
		}
		
		int rellenarTanque(int tipo, int cantidad) {
			
			// No coinciden los tipos
			if(tipo!= tipoCombustible)
				return -1;
			
			if(cantidad > (capacidad-cantidadActual)) 
				return -2;
			
			cantidadActual+=cantidad;
			contadorEntrada+=cantidad;
			return 1;
		}

		int extraerTanque(int cantidad) {	

			// No hay tanta cantidad de combustible
			if(cantidadActual < cantidad ) 
				return -1;
			
			cantidadActual-=cantidad;
			contadorSalida+=cantidad;
			return 1;
		}

		
	}

