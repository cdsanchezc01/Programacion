package MisClases;

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JuegoTiempo juego=new JuegoTiempo();
			
		boolean salir=false;
		while(salir==faslse)
		{
			//MUESTRO EL MENU AL USUARIO
			System.out.println("1-- Juegar a la versión 1");
			System.out.println("2-- Juegar a la versión 2 (conn margen de errores)");
			System.out.println("3-- Salir");
			
			int opcionElegida=teclado.nexInt();
			switch(opcionElegida)
		}
		
		{
		case 1:
				juego.jugarPartida();
				break;
		case 2: juego.jugarPartidaV2();
				break;
	
		
		
		}

	}

}
