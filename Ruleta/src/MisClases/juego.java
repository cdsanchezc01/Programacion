package MisClases;

import java.util.Random;
import java.util.Scanner;

public class juego {	
	
//	ATRIBUTOS
	
	final int NUM_JUGADORES = 3;
	final int NUM_PANELES = 7;
	int turnoActual;
	jugador[]jugadores;

//		METODOS
	
	juego() {
		
//		CREAMOS EL ARRAY PARA LOS JUGADORES
		
		jugadores=new jugador[NUM_JUGADORES];
		
		for(int i=0; i<NUM_JUGADORES;i++) {
			jugadores[i]=new jugador(i);
		}
		
//		ASIGNAMOS EL TURNO INICIAL ALEATORIO
		
		Random numeroAleatorio=new Random();
		turnoActual=numeroAleatorio.nextInt(0,NUM_JUGADORES);
	}
	
	void cambiarTurno(){
		turnoActual++;
		if(turnoActual==NUM_JUGADORES)
			turnoActual=0;
	}
	
	void partida() {
		for(int i=0; i<NUM_PANELES; i++) {
//			CREAMOS EL PANEL CON EL CUAL VAMOS A JUGAR
			Panel miPanel=new Panel("Te has echo mayor si...",
					"Si usas \n pantuflas y bata");
			
//			MOSTRAMOS EL ESTADO DEL PANEL 
			miPanel.mostrarEstadoActual();
			
			int opcion=mostrarMenuTipo1();
			
			if(opcion==1)
//				LANZAMOS LA RULETA
			{
			else if (opcion==2)
//				COMPRAR VOCAL
			}
			
			{
			else if (opcion==3)
//				RESOLVER PANEL
			}
				
			
		int mostrarMenuTipo1() {
			Scanner teclado=new Scanner(System.in)
//			MOSTRAMOS LAS DISTINTAS OPCIONES POR PANTALLA
			System.out.println("1-Tirar ruleta \n2-Comprar vocal\n3-Resolver panel");
			int opcion=teclado.nextInt();
			teclado.nextLine();
			if(opcion>=1 && opcion<=3)
				salir=true;
			}
			
			
		}
	}

}
