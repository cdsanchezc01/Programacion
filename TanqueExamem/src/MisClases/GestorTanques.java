 package MisClases;
	import java.util.Scanner;

	public class GestorTanques {

		
		Tanque[] tanques;
		
		GestorTanques(){
			tanques=new Tanque[10];
			for(int i=0;i<10;i++)
				tanques[i]=new Tanque();
			
			tanques[0].inicializarTanque(3, 8000000);
			tanques[1].inicializarTanque(3, 8000000);		
			tanques[2].inicializarTanque(3, 4000000);		
			tanques[3].inicializarTanque(4, 2000000);		
			tanques[4].inicializarTanque(4, 2000000);		
			tanques[5].inicializarTanque(1, 8000000);
			tanques[6].inicializarTanque(1, 8000000);
			tanques[7].inicializarTanque(1, 8000000);
			tanques[8].inicializarTanque(2, 2000000);		
			tanques[9].inicializarTanque(2, 2000000);		
		}
		
		void rellenarTanque() {
			Scanner teclado=new Scanner(System.in);
			
			
			System.out.print("Introduce el número de tanque :");
			int numero=teclado.nextInt();

			System.out.print("Introduce el tipo de combustible :");
			int tipo=teclado.nextInt();

			System.out.print("Introduce la cantidad :");
			int cantidad=teclado.nextInt();
		
			teclado.nextLine();
			
			int retorno=tanques[numero].rellenarTanque(tipo,cantidad);
			if(retorno==-1)
				System.out.println("Hubo un error. No coincide el tipo de combustible con el que almacena el tanque");
			else if(retorno==-2)
				System.out.println("Hubo un error. No queda espacio para esa cantidad de litros");
			
		}

		void extraerTanque() {
			Scanner teclado=new Scanner(System.in);
			
			System.out.print("Introduce el número de tanque :");
			int numero=teclado.nextInt();

			System.out.print("Introduce la cantidad :");
			int cantidad=teclado.nextInt();
		
			teclado.nextLine();
			
			int retorno=tanques[numero].extraerTanque(cantidad);
			if(retorno==-1)
				System.out.println("Hubo un error. Actualmente, no hay tanta cantidad de combustible");
		}
		
		
		void resumen() {
			Scanner teclado=new Scanner(System.in);
			
			System.out.print("Introduce el tipo de combustible :");
			int tipo=teclado.nextInt();
			teclado.nextLine();
			
			int sumaCapacidad=0;
			int sumaCantidad=0;
			for(int i=0;i<tanques.length;i++) {
				if(tanques[i].tipoCombustible == tipo) {
					sumaCapacidad+= tanques[i].capacidad;
					sumaCantidad+=tanques[i].cantidadActual;
				}
			}
			
			// Muestro la información
			System.out.println("Tipo de combustible: " + tipo);
			System.out.println("Cantidad total: " + sumaCantidad);		
			System.out.println("Capacidad total: " + sumaCapacidad);		
			if(sumaCapacidad!=0)
				System.out.println("Porcentaje ocupación: " + 100*sumaCantidad/sumaCapacidad);
			else System.out.println("Porcentaje ocupación: - ");
			
		}
		
		void mostrarEstadoTanque() {
			Scanner teclado=new Scanner(System.in);
			
			
			System.out.print("Introduce el número de tanque :");
			int numero=teclado.nextInt();
			teclado.nextLine();
			
			System.out.println("Tanque:" + numero + tanques[numero].mostrarInfoTanque() );
		}

		void mostrarEstadoTanques() {
			
			for(int i=0;i<10;i++)
				System.out.println("Tanque:" + i + tanques[i].mostrarInfoTanqueV2() );
		}
		
	}
